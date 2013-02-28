package howbigisthewebpage

/**
 * Defines how a line of text can be written out.
 */
trait LinePrinter {
  /**
   * Write the given line out.
   */
  def println(line : String) : Unit
}