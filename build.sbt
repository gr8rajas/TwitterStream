name := "TwitterStream"

version := "0.1"

scalaVersion := "2.12.7"

val sparkVersion = "2.4.5"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion
)

libraryDependencies ++= Seq(
  "org.apache.spark" %  "spark-streaming-kafka-0-10_2.12" % sparkVersion,
  "org.apache.spark" %  "spark-sql-kafka-0-10_2.12" % sparkVersion
)

libraryDependencies ++= Seq(
"org.apache.bahir" %% "spark-streaming-twitter" % "2.4.0",
"com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
)

