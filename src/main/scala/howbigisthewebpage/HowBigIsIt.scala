package howbigisthewebpage

/**
 * Main entry point into the application
 */
object HowBigIsIt {

  /**
   * Execute the program.  Delegates to the execute method.
   */
  def main(args: Array[String]): Unit = {
    execute(
      // read lines in from the console
      new BufferedInput {
        def readLine() = Console.readLine
      },
      // print lines out to the console
      new LinePrinter {
        def println(line: String) = Console.println(line)
      },
      // create webpages for downloads
      new DownloadFactory {
        def newDownload(url: String): Download = {
          // need to create a new httpclient for each call as not multi-threaded
          return new Webpage(url, newHttpClient)
        }
      })
  }
  
  /**
   * Create a new apache commons http client configuring any parameters required (e.g. timeouts)
   */
  private def newHttpClient : org.apache.http.client.HttpClient = {
    val client = new org.apache.http.impl.client.DefaultHttpClient
    val connectionParams = client.getParams()
    org.apache.http.params.HttpConnectionParams.setConnectionTimeout(connectionParams, 400);
    org.apache.http.params.HttpConnectionParams.setSoTimeout(connectionParams, 600);
    return client
  }

  /**
   * Perform the required application logic:
   * - Read in websites entered by user
   * - download content from given websites
   * - print out content size of given websites
   */
  def execute(input: BufferedInput, output: LinePrinter, downloads: DownloadFactory) = {
    val inputReader = new Input

    // read in sites from the given input
    inputReader.read(input)

    // create webpages from the user input as a parallel collection
    val webpages = (inputReader.sites map
      (s => downloads.newDownload(s))).par

    // download sites and print out content size
    // done in parallel as parallel collection
    webpages.foreach(page =>
      { output.println(page + " " + page.download) })

    // finally print -1 before exiting
    output.println("-1")
  }
}