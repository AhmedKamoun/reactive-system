name := "orc"
version := "1.0"
scalaVersion := "2.12.3"

lazy val commons = RootProject(file("../commons"))
lazy val orc = (project in file(".")).enablePlugins(PlayScala).dependsOn(commons).aggregate(commons)

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"

// Play
libraryDependencies ++= Seq(
  guice,
  filters,
  ws)

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.8"

// akka dependencies
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.8",
  "com.typesafe.akka" %% "akka-cluster-tools" % "2.5.8",
  "com.typesafe.akka" %% "akka-slf4j" % "2.5.8")

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test



