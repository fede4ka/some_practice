name := "notepad"

version := "0.1"

scalaVersion := "2.12.7"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.4"

libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test"