package howbigisthewebpage

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.GivenWhenThen

class InputSpec extends FlatSpec with ShouldMatchers with GivenWhenThen {

  "An Input" should "read a web address" in {
    given("given a new input")
    var input = new Input
    
    when("a web address is read")
    input.read(new java.io.StringReader("www.google.co.uk\n"));
    
    then("then there is one address")
    input.size should equal (1)
    then("and the first address is google")
    input.get(0) should equal ("www.google.co.uk")
  }
  
  it should "read two address seperated by a newline" in {
    given("given a new input")
    var input = new Input
    
    when("when two address are read seperated by newlines")
    input.read(new java.io.StringReader("www.google.co.uk\nwww.bbc.co.uk\n"));
    
    then("then there are two addresses")
    input.size should equal (2)
    then("and the first address is google")
    input.get(0) should equal ("www.google.co.uk")
    then("and the second address is the bbc")
    input.get(1) should equal ("www.bbc.co.uk")
  }
  
  it should "ignore -1 as input" in {
    given("given a new input")
    var input = new Input
    
    when("a web address is read following by -1")
    input.read(new java.io.StringReader("www.google.co.uk\n-1\n"));
    
    then("then there is one address")
    input.size should equal (1)
    then("and the first address is google")
    input.get(0) should equal ("www.google.co.uk")
  }
  
  it should "ignore any input after -1" in {
    given("given a new input")
    var input = new Input
    
    when("-1 is followed by a new line and some content")
    input.read(new java.io.StringReader("-1\nwww.google.co.uk\n"));
    
    then("then there is no address")
    input.size should equal (0)
  }
}