package howbigisthewebpage

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.GivenWhenThen
import org.scalamock.scalatest.MockFactory
import org.scalamock.ProxyMockFactory
import scala.collection.mutable.ArrayBuffer

class WebpageSpec extends FlatSpec with ShouldMatchers with GivenWhenThen with MockFactory with ProxyMockFactory {

  val httpclient = mock[org.apache.http.client.HttpClient]
  
  val httpresponse = mock[org.apache.http.HttpResponse]
  
  val testSubject = new Webpage("www.google.co.uk", httpclient)
  
  def createInputStreamWithXBytes(x : Int) : java.io.InputStream = {
    val dataReturned = new Array[Byte](x)
    return new java.io.ByteArrayInputStream(dataReturned);
  }
  
  "A webpage" should "perform a get request to the correct URL" in {
    given("httpclient expects a get method with the correct URI")
    httpclient expects 'execute where 
    		{ (getmethod : org.apache.http.client.methods.HttpGet) => 
      			getmethod.getURI().getHost() == "www.google.co.uk"}
    
    when("a download is performed")
    testSubject.download
    
    then("the http host is google.co.uk")
    // from the stubbing above
  }
  
  it should "use http to perform requests" in {
    given("httpclient expects a get method with the correct protocol")
    httpclient expects 'execute where 
    		{ (getmethod : org.apache.http.client.methods.HttpGet) => 
      			getmethod.getURI().getScheme() == "http"}
  
    when("a download is performed")
    testSubject.download
    
    then("the protocol is http")
    // from the stubbing above
  }
  
  it should "return the size of the webpage downloaded" in {
    given("httpclient returns a response") 
    httpclient expects 'execute returning httpresponse
    
    given("the http response has a size")
    val entity = mock[org.apache.http.HttpEntity]
    entity expects 'getContent returning createInputStreamWithXBytes(123456)
    httpresponse expects 'getEntity returning entity

    when("a download is performed")
    val pagesize = testSubject.download
    
    then("the returned size should match the response size")
    pagesize should equal ("123456")
  }
  
  it should "return a * if there is a null response" in {
    given("httpclient returns a response") 
    httpclient expects 'execute returning null

    when("a download is performed")
    val pagesize = testSubject.download
    
    then("the returned value should be \"*\"")
    pagesize should equal ("*")
  }
  
  it should "return a * if there is an error" in {
    given("httpclient returns a response") 
    httpclient expects 'execute throws new java.io.IOException("Thrown by test")

    when("a download is performed")
    val pagesize = testSubject.download
    
    then("the returned value should be \"*\"")
    pagesize should equal ("*")
  }
}