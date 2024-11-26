// See README.md for license details.

ThisBuild / scalaVersion := "2.13.15"
ThisBuild / version := "0.1.0"
ThisBuild / organization := "com.github.nolanp"

val chiselVersion = "6.6.0"

lazy val root = (project in file("."))
  .settings(
    name := "",
    libraryDependencies ++= Seq(
      "org.chipsalliance" %% "chisel" % chiselVersion,
      "org.scalatest" %% "scalatest" % "3.2.19" % "test"
    ),
    scalacOptions ++= Seq(
      "-language:reflectiveCalls",
      "-deprecation",
      "-feature",
      "-Xcheckinit",
      "-Ymacro-annotations"
    ),
    addCompilerPlugin(
      "org.chipsalliance" % "chisel-plugin" % chiselVersion cross CrossVersion.full
    )
  )
