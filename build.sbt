import scala.sys.process.*
import scala.language.postfixOps
import scala.scalanative.build.*

ThisBuild / version := "0.1.0"
ThisBuild / organization := "io.github.rustfields"
ThisBuild / scalaVersion := "3.3.3"

// Native settings

lazy val core = (project in file("core"))
  .settings(
    name := "RuFi-core-wrapper",
    logLevel := Level.Info,
    nativeConfig ~= { c =>
      c.withLTO(LTO.none) // thin
        .withMode(Mode.debug) // releaseFast
        .withGC(GC.immix) // commix
    },
    libraryDependencies += "org.scalatest" % "scalatest_native0.4_3" % "3.2.16",
  ).enablePlugins(ScalaNativePlugin)

// Tasks definition

lazy val cargoBuild = taskKey[Unit]("Compiles Rust native library")
cargoBuild := {
  "./bash-scripts/cargo-build.sh" !
}

lazy val cargoTest = taskKey[Unit]("Tests Rust native library")
cargoTest := {
  "./bash-scripts/cargo-test.sh" !
}

lazy val generateHeaders = taskKey[Unit]("generate C headers")
generateHeaders := {
  "./bash-scripts/generate-headers.sh" !
}