# Kafka-Spark-Streaming-Hive

## Kafka

First Download Apache Kafka and extract it to ~/Downloads/  Then run the following commands to Start Kafka Server


`cd /home/cloudera/Downloads/kafka_2.12-2.5.0`

`bin/kafka-server-start.sh config/server.properties`

***Creating Topic***
 
`bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic tweets_data`

To **make Sure** that topic is created list all topics 

`bin/kafka-topics.sh --list --bootstrap-server localhost:9092`

## Hive 

First make HDFS directory to save parquet files in it which is streamed from spark 

IF you got **Name node is in safe mode**!. Then try :
`'sudo -u hdfs hdfs dfsadmin -safemode leave'`

Then create Directory :
`'sudo -u hdfs hadoop fs -mkdir -p /user/hive/warehouse/tweets_staging/' `

Also we need to change hdfs owner to cloudera run this command :
`'sudo -u hdfs hadoop fs -chown cloudera /user/hive/warehouse/tweets_staging/'`

then **Create Table** in Hive :

`CREATE EXTERNAL TABLE tweets_data (id BIGINT  ,text string , lang string , user_id BIGINT  , user_name string , user_screenName string , user_location string , user_followersCount int , retweetCount int , favoriteCount int ) STORED AS PARQUET LOCATION '/user/hive/warehouse/tweets_staging/';`





