name := "data"

version := "1.0"

lazy val `data` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

scalaVersion := "2.11.11"

lazy val akkaVersion = "2.5.11"

libraryDependencies ++=
  Seq(
    "com.typesafe.play" %% "play-slick" % "3.0.1",
    "org.postgresql" % "postgresql" % "42.1.4",
    jdbc,
    ehcache,
    ws,
    specs2 % Test,
    guice)

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

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

      