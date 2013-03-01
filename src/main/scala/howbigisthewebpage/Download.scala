package howbigisthewebpage

/**
 * Defining how something can be downloaded
 */
trait Download {
  /**
   * Perform the download and return the content length or an error response "*"
   */
  def download: String
}