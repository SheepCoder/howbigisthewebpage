package howbigisthewebpage

class Webpage(url : String, httpclient : org.apache.http.client.HttpClient) {
  val ERROR_RESPONSE = "*"
    
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