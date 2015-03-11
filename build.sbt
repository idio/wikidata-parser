import AssemblyKeys._

assemblySettings

name := "wikidata"

version := "1.0"

scalaVersion := "2.10.3"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.2.0"

libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.2.11"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"