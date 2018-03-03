name := "processor"
version := "1.0"
scalaVersion := "2.12.3"

lazy val commons = RootProject(file("../commons"))
lazy val processor = (project in file(".")).dependsOn(commons).aggregate(commons)

// Guice
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-guice" % "2.6.12",
  "com.typesafe.play" %% "play-json" % "2.6.9")


// akka dependencies
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.8",
  "com.typesafe.akka" %% "akka-cluster-tools" % "2.5.8",
  "com.typesafe.akka" %% "akka-slf4j" % "2.5.8")

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test



