package howbigisthewebpage

/**
 * Creates instances of webpages
 */
trait DownloadFactory {
  /**
   * Create a new download for the given URL
   */
	def newDownload(url : String) : Download
}