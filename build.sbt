name := "Test Charged"
crossScalaVersions := Seq("2.11.12", "2.12.8")

inThisBuild(List(
  organization := "com.github.fulrich",
  homepage := Some(url("https://github.com/fulrich/testcharged")),
  licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
  developers := List(
    Developer(
      "fulrich",
      "fulrich",
      "9284621+fulrich@users.noreply.github.com",
      url("https://github.com/fulrich")
    )
  )
))

// Dependencies
val ScalacticVersion = "3.0.5"
val ScalaCheckVersion = "1.14.0"

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % ScalaCheckVersion,
  "org.scalactic" %% "scalactic" % ScalacticVersion,
  "org.scalatest" %% "scalatest" % ScalacticVersion % "test",
)

// Documentation
lazy val micrositeSettings = Seq(
  micrositeName := "Test Charged",
  micrositeDescription := "Supercharge your testing",
  micrositeBaseUrl := "/test-charged",
  micrositeDocumentationUrl := "/test-charged/docs",
  micrositeAuthor := "fulrich",
  micrositeHomepage := "https://fulrich.github.io/test-charged/",
  micrositeGithubOwner := "fulrich",
  micrositeGithubRepo := "test-charged",
  micrositeHighlightTheme := "darcula",
  micrositeGithubToken := sys.env.get("GITHUB_TOKEN"),
  micrositePushSiteWith := GitHub4s,
  micrositeGitterChannel := false,
  micrositeShareOnSocial := false,
  micrositeFooterText := None
)

lazy val root = project in file(".")

lazy val docs = (project in file("docs")).
  enablePlugins(MicrositesPlugin).
  settings(publishArtifact := false).
  settings(micrositeSettings)

// Publishing
publishConfiguration := publishConfiguration.value.withOverwrite(true)
publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)