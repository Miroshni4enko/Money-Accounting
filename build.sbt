import play.sbt.PlayImport.PlayKeys.playRunHooks

name := """Money Accounting"""
organization := "com.vimi"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.2"

libraryDependencies ++= Seq (
  guice,
  jdbc,
  ws
  )

playRunHooks ++= Seq(
  Webpack
)

routesGenerator := InjectedRoutesGenerator

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"