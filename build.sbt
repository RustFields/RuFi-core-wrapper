import sbtcrossproject.CrossPlugin.autoImport.crossProject

ThisBuild / version := "0.1.0"
ThisBuild / organization := "io.github.rustfields"

lazy val core = crossProject(JVMPlatform, NativePlatform)
  .settings(
    scalaVersion := "3.3.0",
  )
  .nativeSettings(
    nativeLinkStubs := true,
    nativeLinkingOptions ++= {
      val path = s"${baseDirectory.value}/native/target/release"
      val library = "operations"
      Seq(s"-L$path", "-rpath", path, s"-l$library")
    },
  )

lazy val coreJVM    = core.jvm
lazy val coreNative = core.native