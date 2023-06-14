import sbtcrossproject.CrossPlugin.autoImport.crossProject
import scala.sys.process.*
import scala.language.postfixOps

ThisBuild / version := "0.1.0"
ThisBuild / organization := "io.github.rustfields"

lazy val core = crossProject(JVMPlatform, NativePlatform)
  .settings(
    scalaVersion := "3.3.0",
    libraryDependencies += "org.scalatest" % "scalatest_native0.4_3" % "3.2.16",
  )
  .nativeSettings(
    nativeLinkStubs := true,
    nativeLinkingOptions ++= {
      val path = s"${baseDirectory.value}/../../native/target/release"
      val library = "rufi_core_wrapper"
      Seq(s"-L$path", "-rpath", path, s"-l$library")
    },
  )

lazy val coreJVM    = core.jvm
lazy val coreNative = core.native

lazy val cargoBuild = taskKey[Unit]("Compiles Rust native library")
cargoBuild := {
  "./bash-scripts/cargo-build.sh" !
}

lazy val cargoTest = taskKey[Unit]("Tests Rust native library")
cargoTest := {
  "./bash-scripts/cargo-test.sh" !
}