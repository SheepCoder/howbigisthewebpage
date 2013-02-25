package howbigisthewebpage

import scala.collection.mutable.ListBuffer

class Input {

  var sites = new ListBuffer[String]
  
  def size() : Int = sites.size
  
  def read(reader: java.io.Reader) = {
    sites += "www.google.co.uk";
  }
  
  def get(index : Int) : String = sites(index)
}