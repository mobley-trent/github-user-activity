val zioVersion = "2.0.15"
val sttpVersion = "3.10.3"

lazy val root = project
  .in(file("."))
  .settings(
    name := "github-user-activity",
    version := "0.1.0-SNAPSHOT",

    libraryDependencies += "dev.zio" %% "zio-test-sbt" % zioVersion % Test
  )

enablePlugins(JavaAppPackaging)
enablePlugins(ScalafmtPlugin)
addCompilerPlugin(scalafixSemanticdb)

scalacOptions ++= Seq(
  "-deprecation",
  "-Ywarn-unused",
)

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % zioVersion,
  "dev.zio" %% "zio-json" % "0.6.2",
  "dev.zio" %% "zio-test" % zioVersion % Test,
  "com.softwaremill.sttp.client3" %% "zio1-json" % sttpVersion
)

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")