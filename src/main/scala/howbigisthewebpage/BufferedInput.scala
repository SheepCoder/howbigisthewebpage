package howbigisthewebpage

/**
 * Defines how a single line can be read and returned.
 */
trait BufferedInput {
  /**
   * Read a line of text and return it.
   */
  def readLine(): String
}