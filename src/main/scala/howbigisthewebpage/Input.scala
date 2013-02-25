package howbigisthewebpage

import scala.collection.mutable.ListBuffer

class Input {

  var sites = new ListBuffer[String]
  
  def size() : Int = sites.size
  
  def read(reader: java.io.Reader) = {
    val buffer = createBufferedReader(reader);
    var line : String = buffer.readLine()
    
    while (line != null && !"-1".equals(line)) {
    	sites += line
    	line = buffer.readLine()
    }
  }
  
  private def createBufferedReader(reader : java.io.Reader) : java.io.BufferedReader = new java.io.BufferedReader(reader)
  
  def get(index : Int) : String = sites(index)
}