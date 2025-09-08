import xerial.sbt.Sonatype.sonatypeCentralHost

name := "Test Charged"

inThisBuild(
  List(
    organization := "com.garnercorp",
    homepage := Some(url("https://github.com/garnercorp/test-charged")),
    licenses := List(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    scalaVersion := "3.7.2",
    crossScalaVersions := Seq("2.12.20", "2.13.16", "3.7.2"),
    developers := List(
      Developer(
        "vmandrychenko",
        "vmandrychenko",
        "4511527+vmandrychenko@users.noreply.github.com",
        url("https://github.com/vmandrychenko")
      ),
      Developer(
        "fulrich",
        "fulrich",
        "9284621+fulrich@users.noreply.github.com",
        url("https://github.com/fulrich")
      )
    ),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/garnercorp/test-charged"),
        "scm:git@github.com:garnercorp/test-charged.git"
      )
    ),
    libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % "always",
  )
)

// Dependencies (intentionally spaced to avoid merge conflicts from GitHub auto-bump bots)
val ScalacticVersion = "3.2.19"

val ScalaCheckVersion = "1.19.0"

val ScalaCheckPlusVersion = "3.2.19.0"

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % ScalaCheckVersion,
  "org.scalactic" %% "scalactic" % ScalacticVersion,
  "org.scalatest" %% "scalatest" % ScalacticVersion % Test,
  "org.scalatestplus" %% "scalacheck-1-18" % ScalaCheckPlusVersion % Test
)

lazy val root = project in file(".")

// Publishing
pomIncludeRepository := { _ => false }
publishMavenStyle := true
sonatypeCredentialHost := sonatypeCentralHost
publishTo := sonatypePublishToBundle.value
