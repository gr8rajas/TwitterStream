import java.util.Properties

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.spark.sql.SparkSession
import twitter4j.conf.ConfigurationBuilder

trait configs {


  val spark = SparkSession.builder.master("local[4]").appName("AnalyzeStreams").getOrCreate()
  spark.conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
  spark.sparkContext.setLogLevel("ERROR")


  val kafkaCfg = {
    val properties = new Properties()
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.LongSerializer")
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    properties
  }


  val cb = new ConfigurationBuilder
  cb.setOAuthConsumerKey("kBxhubHNb3sPhq1eSoWSdxf5K")
    .setOAuthConsumerSecret("GZE3Lp0WklDmhBCvDSsTSNpf87nM71PxP5jB5g0pZm49g6vXtW")
    .setOAuthAccessToken("295114956-4mFygJ0rqNg0k7jY5ZnnETG6jiNGcvNunapvs6sm")
    .setOAuthAccessTokenSecret("TUkrZNNEHWC8Xz2UDfPKPI0QhDemovnUQlCZHqd3u1o98")




}
