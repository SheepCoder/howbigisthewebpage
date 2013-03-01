package howbigisthewebpage

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.GivenWhenThen
import org.scalamock.scalatest.MockFactory
import org.scalamock.ProxyMockFactory

/**
 * Component tests for the application confirming components are operating correctly together.
 */
class HowBigIsItSpec extends FlatSpec with ShouldMatchers with GivenWhenThen with MockFactory with ProxyMockFactory  {

  val mockWebpageFactory = mock[DownloadFactory]
  
  val mockLinePrinter = mock[LinePrinter]
  
  "The application" should "read in each line and create a webpage" in {
    given("a user enters a single website followed by -1")
    val bufferedInput = new java.io.BufferedReader(new java.io.StringReader("www.bbc.co.uk\n-1\n")) with BufferedInput
    
    mockWebpageFactory expects 'newDownload withArgs("www.bbc.co.uk") returning new Download() {
      def download : String = {""}
    }
    
    when("the application is executed")
    HowBigIsIt.execute(bufferedInput, new LinePrinter() {
      def println(s : String) : Unit = {}
    }, mockWebpageFactory)
    
    then("a single webpage is created with the correct url")
    // see expectation on mockWebpageFactory above
  }
  
  it should "result in the download being downloaded" in {
    given("a user enters a single website followed by -1")
    val bufferedInput = new java.io.BufferedReader(new java.io.StringReader("www.bbc.co.uk\n-1\n")) with BufferedInput
    
    given("a webpage is created with the correct url")
    val mockDownload = mock[Download]
    mockDownload expects 'download
    mockWebpageFactory expects 'newDownload withArgs("www.bbc.co.uk") returning mockDownload
    
    when("the application is executed")
    HowBigIsIt.execute(bufferedInput, new LinePrinter() {
      def println(s : String) : Unit = {}
    }, mockWebpageFactory)
    
    then("the webpage is downloaded")
    // see expectation on mockDownload
  }
  
  it should "result in the content size being printed out" in {
    given("a user enters a single website followed by -1")
    val bufferedInput = new java.io.BufferedReader(new java.io.StringReader("www.bbc.co.uk\n-1\n")) with BufferedInput
    
    given("a webpage is created with the correct url which is downloaded")
    val mockDownload = mock[Download]
    mockDownload expects 'download returning "8888"
    mockWebpageFactory expects 'newDownload withArgs("www.bbc.co.uk") returning mockDownload
    
    mockLinePrinter expects 'println withArgs mockDownload + " 8888"
    mockLinePrinter expects 'println withArgs "-1"
    
    when("the application is executed")
    HowBigIsIt.execute(bufferedInput, mockLinePrinter, mockWebpageFactory)
    
    then("the webpage and its size are printed out, followed by -1")
    // see expectation on mockLinePrinter
  }
}