val scala3Version = "3.6.3"
val zioVersion = "2.0.15"
val sttpVersion = "3.10.3"

lazy val root = project
  .in(file("."))
  .settings(
    name := "github-user-activity",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "dev.zio" %% "zio-test-sbt" % zioVersion % Test
  )


libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % zioVersion,
  "dev.zio" %% "zio-json" % "0.6.2",
  "dev.zio" %% "zio-test" % zioVersion % Test,
  "com.softwaremill.sttp.client3" %% "zio1-json" % sttpVersion
)

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")