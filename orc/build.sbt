name := "orc"
version := "1.0"
scalaVersion := "2.12.3"

lazy val akkaVersion = "2.5.11"

lazy val commons = RootProject(file("../commons"))
lazy val orc = (project in file(".")).enablePlugins(PlayScala).dependsOn(commons).aggregate(commons)

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"

// Play
libraryDependencies ++= Seq(
  guice,
  filters,
  ws)

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.9"

// akka dependencies
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion)

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test



