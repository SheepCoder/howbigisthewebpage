package howbigisthewebpage

/**
 * Represents a webpage in the system.  Created with the URL (minus protocol) and an apache commons httpclient
 */
class Webpage (url : String, httpclient : org.apache.http.client.HttpClient) extends Object with Download {
  /**
   * The error response from the download method.
   */
  val ERROR_RESPONSE = "*"
   
  /**
   * download the webpage at the URL given during construction.  Returns either a string representing the 
   * content length downloaded, or the error response if an error occurs.
   */
  def download : String = {
    try {
	    val get = new org.apache.http.client.methods.HttpGet("http://" + url)
	    val response = httpclient.execute(get)
	    
	    if (response != null) {
	      return contentSize(response.getEntity().getContent()).toString
	    } else {
	      return ERROR_RESPONSE
	    }
    } catch {
      case e : Exception => return ERROR_RESPONSE
    }
  }
  
  private def contentSize(input : java.io.InputStream) : Int = {
    var count = 0;
    
    while (input.read() != -1) {count += 1}
    
    return count
  }
  
  override def toString() : String = url
}