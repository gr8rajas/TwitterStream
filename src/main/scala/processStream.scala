
import com.typesafe.scalalogging.StrictLogging
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.spark.sql.streaming.{OutputMode, Trigger}

object processStream extends Serializable with configs with StrictLogging {

  def main(args: Array[String]): Unit = {


 /** Reading Data from Kafka */
    val bootstrapServer = kafkaCfg.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG)

    val readSourceTopic = spark.readStream.format("kafka")
      .option("kafka.bootstrap.servers", s"$bootstrapServer")
      .option("subscribe", "test")
      .option("startingOffsets", "earliest")
      .load()
    //    readSourceTopic.printSchema()


    /** Deserialize Data from Kafka */
    val deserializeData  = readSourceTopic.selectExpr("CAST(key AS STRING)",
      "CAST(value AS STRING)",
      "timestamp",
      "offset",
      "CASE WHEN CAST(value AS STRING) LIKE '%https%' THEN 1 ELSE 0 END AS url_count"
    )


    /** Compute Total Tweet Counts and URL counts with minTime and MaxTime*/
    val aggregatedData = writeStreamToSource.computeAggData(deserializeData)


    /** Extract Tweets from Deserialize Data*/
    import spark.implicits._
    val extractTweet = deserializeData.map(tweet => tweet(1).toString)


    /** Average Number of Tweets per Second/Minute/Hour */
    val avgTweets = writeStreamToSource.computeAvgNoOfTweets(aggregatedData)

    /** Retrieve Top 20 Hash Tags */
    val topHashTags = writeStreamToSource.computeTopHashTags(extractTweet)

    /** Retrieve Top 20 URLs */
    val topUrls = writeStreamToSource.computeTopUrls(extractTweet)


    logger.info("Starting Stream")
    avgTweets.writeStream.outputMode(OutputMode.Complete()).format("console").option("truncate", false).trigger(Trigger.ProcessingTime("1 seconds")).start()
    topHashTags.writeStream.outputMode(OutputMode.Complete()).format("console").option("truncate", false).trigger(Trigger.ProcessingTime("5 second")).start()
    topUrls.writeStream.outputMode(OutputMode.Complete()).format("console").option("truncate", false).trigger(Trigger.ProcessingTime("5 second")).start()


    spark.streams.awaitAnyTermination()


  }

}
