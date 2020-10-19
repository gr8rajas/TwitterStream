import org.apache.spark.sql
import org.apache.spark.sql.{Dataset, Row}
import org.apache.spark.sql.types.LongType
import org.apache.spark.sql.functions._

object writeStreamToSource extends Serializable with configs {


  def computeAggData(readSource: sql.DataFrame):sql.DataFrame = {

    val aggDataColumns = readSource.groupBy().agg(
      count("value").cast(LongType).as("tweet_count"),
      min("timestamp").cast(LongType).as("min_time"),
      max("timestamp").cast(LongType).as("max_time"),
      sum("url_count").cast(LongType).as("url_count")
    )

    aggDataColumns
  }


  def computeAvgNoOfTweets(aggData:sql.DataFrame):sql.DataFrame ={

    val avgTweets = aggData
      .withColumn("time_diff_sec",round(aggData("max_time") - aggData("min_time")))
      .withColumn("avg_tweets_per_sec",aggData("tweet_count")/col("time_diff_sec"))
      .withColumn("avg_tweets_per_min",aggData("tweet_count")/(col("time_diff_sec")/60) )
      .withColumn("avg_tweets_per_hour",aggData("tweet_count")/(col("time_diff_sec")/3600 ))

    val validData = avgTweets.withColumn("url_tweets_perc",col("url_count")/col("tweet_count")*100)

    val selectAvgColumns = validData.select("tweet_count",
      "url_count",
      "avg_tweets_per_sec",
      "avg_tweets_per_min",
      "avg_tweets_per_hour",
      "url_tweets_perc"
    )

    selectAvgColumns
  }


  def computeTopHashTags(extractTweet :Dataset[String]):Dataset[Row] ={

    import spark.implicits._
    val extractHashTag = extractTweet.flatMap(hashTag => hashTag.split(" ")
      .filter(tag => tag.startsWith("#")))

    val countHashTags = extractHashTag.map(hashTag => (hashTag,1))

    val topHashTags = countHashTags.groupBy(col("_1").as("hashTag"))
      .agg(sum("_2").as("cnt"))
      .orderBy(desc("cnt")
      )

    topHashTags
  }


  def computeTopUrls(extractTweet :Dataset[String]):Dataset[Row] ={

    import spark.implicits._

    val extractURLTag = extractTweet.flatMap(hashTag => hashTag.split(" ")
      .filter(tag => tag.startsWith("https://")))

    val countUrlTags = extractURLTag.map(hashTag => (hashTag,1))

    val topUrlTags = countUrlTags.groupBy(col("_1").as("UrlTag"))
      .agg(sum("_2").as("cnt"))
      .orderBy(desc("cnt")
      )

    topUrlTags
  }



}
