package howbigisthewebpage

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class InputSpec extends FlatSpec with ShouldMatchers {

  "An Input" should "read a web address" in {
    var input = new Input
    input.read(new java.io.StringReader("www.google.co.uk\n"));
    input.size should equal (1)
    input.get(0) should equal ("www.google.co.uk")
  }
}