name := "processor"
version := "1.0"
scalaVersion := "2.12.3"

lazy val commons = RootProject(file("../commons"))
lazy val processor = (project in file(".")).dependsOn(commons).aggregate(commons)
lazy val akkaVersion = "2.5.11"

// Play modules
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-guice" % "2.6.12",
  "com.typesafe.play" %% "play-json" % "2.6.9")

// akka dependencies
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion)

//Logging
libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.8.0",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test



