package howbigisthewebpage

/**
 * Main entry point into the application
 */
object HowBigIsIt {

  /**
   * Execute the progam
   */
  def main(args: Array[String]): Unit = {
    execute(
      new BufferedInput {
        def readLine() = Console.readLine
      },
      new LinePrinter {
        def println(line: String) = Console.println(line)
      },
      new DownloadFactory {
        def newDownload(url: String): Download = {
          return new Webpage(url, new org.apache.http.impl.client.DefaultHttpClient)
        }
      })
  }

  def execute(input: BufferedInput, output: LinePrinter, downloads: DownloadFactory) = {
    val inputReader = new Input

    // read in sites from the given input
    inputReader.read(input)

    // create webpages from the user input as a parallel collection
    // need to create a new httpclient for each call as not multi-threaded
    val webpages = (inputReader.sites map
      (s => downloads.newDownload(s))).par

    // download sites and print out content size
    // done in parallel as parallel collection
    webpages.foreach(page =>
      { output.println(page + " " + page.download) })

    // finally print -1 before existing
    output.println("-1")
  }
}