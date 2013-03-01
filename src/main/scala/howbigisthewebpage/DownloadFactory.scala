package howbigisthewebpage

/**
 * Creates instances of downloadable items
 */
trait DownloadFactory {
  /**
   * Create a new download for the given URL
   */
	def newDownload(url : String) : Download
}