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
   * Download the webpage at the URL given during construction.  Returns either a string representing the 
   * content length downloaded, or the error response if no response received or an error occurs.
   */
  def download : String = {
    try {
    	// create the get request
	    val get = new org.apache.http.client.methods.HttpGet("http://" + url)
	    
	    // execute the request
	    val response = httpclient.execute(get)
	    
	    // check the response
	    if (response != null) {
	      // if we have one then return the total number of bytes
	      return contentSize(response.getEntity().getContent()).toString
	    } else {
	      // if we do not have one then return an error response
	      return ERROR_RESPONSE
	    }
    } catch {
      // for any error return an error response
      case e : Exception => return ERROR_RESPONSE
    }
  }
  
  /**
   * Returns the number of bytes read from the given input stream
   */
  private def contentSize(input : java.io.InputStream) : Int = {
    var totalBytesRead = 0;
    
    val buffer = new Array[Byte](1000)
    var bytesRead = 0
    
    while (bytesRead != -1) {
      totalBytesRead += bytesRead
      bytesRead = input.read(buffer)
    }
    
    return totalBytesRead
  }
  
  override def toString() : String = url
}