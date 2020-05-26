import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types._

object SparkStreamKafka extends App {

  val IP = "localhost"
  val TOPIC = "twitter_data"
  //val path = "./"

  // Defining My Schema which is the same in Hive table created
  val schema = StructType(Seq(
    StructField("id", LongType, true),
    StructField("text", StringType, true),
    StructField("lang", StringType, true),
    StructField("user_id", LongType, true),
    StructField("user_name", StringType, true),
    StructField("user_screenName", StringType, true),
    StructField("user_location", StringType, true),
    StructField("user_followersCount", IntegerType, true),
    StructField("retweetCount", IntegerType, true),
    StructField("favoriteCount", IntegerType, true)
  ))
  //Starting Spark session
  val spark = SparkSession
    .builder()
    .appName("kafka-consumer")
    .master("local[*]")
    .getOrCreate()

  import spark.implicits._

  spark.sparkContext.setLogLevel("WARN")

  // Reading from Kafka Stream of data from topic and port specified
  val ds1 = spark
    .readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", IP + ":9092")
    .option("zookeeper.connect", IP + ":2181")
    .option("subscribe", TOPIC)
    .option("startingOffsets", "earliest")
    .option("max.poll.records", 10)
    .option("failOnDataLoss", false)
    .load()

  // Reading with The schema specified early
  // Taking only english Tweets only Also avoiding null rows which has nullable id
  val ds_tweets = ds1.selectExpr("cast (value as string) as json").select(from_json($"json", schema=schema)
    .as("data")).select("data.id", "data.text","data.lang"
                                               ,"data.user_id","data.user_name","data.user_screenName"
                                               ,"data.user_location","data.user_followersCount"
                                               ,"data.retweetCount","data.favoriteCount")
                    .filter(col("data.lang").contains("en"))
                    .filter((col("data.id").isNotNull));


  println(ds_tweets.isStreaming)


  // Writing to HDFS as parquet files in the specified directory
  val data = ds_tweets.writeStream
    .format("parquet")
    .outputMode("append")
    .option("path", "hdfs://quickstart.cloudera/user/hive/warehouse/tweets_staging/")
    .option("checkpointLocation", "/home/cloudera/TwitterCheckPoint")
    .option("startingOffsets", "earlist")
    .outputMode("append")
    .start()


  /*
  //Writing to Console to Display Table
  val data = ds_tweets.writeStream
    .format("console")
    .outputMode("append")
    .start()
*/


  spark.streams.awaitAnyTermination()
  spark.stop()

}
