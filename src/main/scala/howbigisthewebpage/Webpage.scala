package howbigisthewebpage

/**
 * Represents a webpage in the system.  Created with the URL (minus protocol) and an apache commons httpclient
 */
class Webpage(url : String, httpclient : org.apache.http.client.HttpClient) {
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
	      return response.getEntity().getContentLength().toString
	    } else {
	      return ERROR_RESPONSE
	    }
    } catch {
      case e : Exception => return ERROR_RESPONSE
    }
  }
}