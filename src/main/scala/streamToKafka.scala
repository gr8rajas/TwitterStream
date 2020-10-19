
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.twitter.TwitterUtils
import twitter4j.auth.OAuthAuthorization
import com.typesafe.scalalogging.StrictLogging


object streamToKafka extends Serializable with configs with StrictLogging {

  def main(args: Array[String]): Unit = {


    val auth = new OAuthAuthorization(cb.build())
    val ssc = new StreamingContext(spark.sparkContext, Seconds(1))

    val tweets = TwitterUtils.createStream(ssc, Some(auth))
    val kvTweets = tweets.map(rawTweet => (rawTweet.getId, rawTweet.getText))

    println("Streaming Tweets to Kafka")

    kvTweets.foreachRDD { (rdd,batchId) =>

      println(s"Processing Records for Batch $batchId")

      rdd.foreach{message  =>
          val producer = new KafkaProducer[Long, String](kafkaCfg)
          producer.send(new ProducerRecord("test", message._1, message._2))
          producer.flush()
        }

      }


    ssc.start()
    ssc.awaitTermination()

  }
}

