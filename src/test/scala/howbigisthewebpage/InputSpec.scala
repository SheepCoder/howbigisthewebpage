package howbigisthewebpage

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.GivenWhenThen

class InputSpec extends FlatSpec with ShouldMatchers with GivenWhenThen {

  "An Input" should "read a web address" in {
    given("a new input")
    var input = new Input
    
    when("a web address is read")
    input.read(new java.io.StringReader("www.google.co.uk\n"));
    
    then("there is one address")
    input.size should equal (1)
    then("the first address is google")
    input.get(0) should equal ("www.google.co.uk")
  }
  
  it should "read two addresses seperated by a newline" in {
    given("a new input")
    var input = new Input
    
    when("two addresses are read seperated by newlines")
    input.read(new java.io.StringReader("www.google.co.uk\nwww.bbc.co.uk\n"));
    
    then("there are two addresses")
    input.size should equal (2)
    then("the first address is google")
    input.get(0) should equal ("www.google.co.uk")
    then("the second address is the bbc")
    input.get(1) should equal ("www.bbc.co.uk")
  }
  
  it should "ignore -1 as input" in {
    given("a new input")
    var input = new Input
    
    when("a web address is read following by -1")
    input.read(new java.io.StringReader("www.google.co.uk\n-1\n"));
    
    then("there is one address")
    input.size should equal (1)
    then("the first address is google")
    input.get(0) should equal ("www.google.co.uk")
  }
  
  it should "ignore any input after -1" in {
    given("a new input")
    var input = new Input
    
    when("-1 is followed by a new line and some content")
    input.read(new java.io.StringReader("-1\nwww.google.co.uk\n"));
    
    then("there is no address")
    input.size should equal (0)
  }
}