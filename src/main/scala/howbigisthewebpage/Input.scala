package howbigisthewebpage

import scala.collection.mutable.ListBuffer

/**
 * Class for handling user input.
 */
class Input {

  var sites = new ListBuffer[String]
  
  /**
   * Returns the number of sites read in.
   */
  def size() : Int = sites.size
  
  /**
   * Reads from the given input one line at a time, each line is considered a site.
   * Will continue to read until a line contains "-1".
   */
  def read(reader: java.io.Reader) = {
    val buffer = createBufferedReader(reader);
    var line : String = buffer.readLine()
    
    while (line != null && !"-1".equals(line)) {
    	this += line
    	line = buffer.readLine()
    }
  }
  
  private def createBufferedReader(reader : java.io.Reader) : java.io.BufferedReader = new java.io.BufferedReader(reader)
  
  /**
   * Return the site at the given index.
   */
  def get(index : Int) : String = sites(index)
  
  /**
   * Add a new site to the list of sites
   */
  def += (site : String) = {
    if (site.contains("://")) {
      throw new IllegalArgumentException("invalid input recieved, websites should not include the http:// prefix")
    } else {
      sites += site
    }
  }
}