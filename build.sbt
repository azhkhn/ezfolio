name := """reactive-stocks"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  ws, // Play's web services module
  "com.typesafe.akka" %% "akka-actor" % "2.3.4",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.4",
  "org.webjars" % "bootstrap" % "2.3.1",
  "org.webjars" % "flot" % "0.8.0",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.4" % "test"
)

libraryDependencies += "com.google.inject" % "guice" % "3.0"

libraryDependencies += "com.google.code.gson" % "gson" % "1.7.1"

libraryDependencies ++= Seq(
  javaWs
)
