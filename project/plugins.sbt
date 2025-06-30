ThisBuild / libraryDependencySchemes ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)

// Formatting
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.5")

// Code Coverage
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "2.3.1")

// Deployment
addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.3.1")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.12.2")
