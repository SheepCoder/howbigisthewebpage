name := "howbigisthewebpage"

version := "1.0"

scalaVersion := "2.9.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "2.4" % "test"

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.2.3"

resolvers += "sonatype" at "https://oss.sonatype.org/content/repositories/releases/"
