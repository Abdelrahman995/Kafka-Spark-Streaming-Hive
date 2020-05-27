# Kafka-Spark-Streaming-Hive Project

## Project Architecture

![Architecture img](/scrnShots/Arch.png)

## **Twitter-Producer**

Producer Part is from this [repository](https://github.com/dbsheta/kafka-twitter-producer/)

OutPut Should be like 
![Producer.PNG](/scrnShots/Producer.PNG)


## Kafka

First Download Apache Kafka and extract it to ~/Downloads/  Then run the following commands to Start Kafka Server


`cd /home/cloudera/Downloads/kafka_2.12-2.5.0`

`bin/kafka-server-start.sh config/server.properties`

***Creating Topic in Kafka***
 
`bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic tweets_data`

To **make Sure** that topic is created list all topics 

`bin/kafka-topics.sh --list --bootstrap-server localhost:9092`

## Spark 

Reading from kafka topic and when you uncomment the print on console part output should be like

![SparkOut.PNG](/scrnShots/SparkOut.PNG)

## Hadoop HDFS 

Data should be writtern in **Parquet Files** like 
![ParquetFiles PNG](/scrnShots/ParquetFiles.PNG)

## Hive 

First make HDFS directory to save parquet files in it which is streamed from spark 

IF you got **Name node is in safe mode**!. Then try :
`'sudo -u hdfs hdfs dfsadmin -safemode leave'`

Then create Directory :
`'sudo -u hdfs hadoop fs -mkdir -p /user/hive/warehouse/tweets_staging/' `

Also we need to change hdfs owner to cloudera run this command :
`'sudo -u hdfs hadoop fs -chown cloudera /user/hive/warehouse/tweets_staging/'`

then ***Create Table in Hive***  :

`CREATE EXTERNAL TABLE tweets_data (id BIGINT  ,text string , lang string , user_id BIGINT  , user_name string , user_screenName string , user_location string , user_followersCount int , retweetCount int , favoriteCount int ) STORED AS PARQUET LOCATION '/user/hive/warehouse/tweets_staging/';`

## Time to visualize 

Using this ip  

![NetworkConfVM.PNG](/scrnShots/NetworkConfVM.PNG)


In Tableau cloudera Hadoop connection 

![Tableau.PNG](/scrnShots/Tableau.PNG)

Here is some charts: 

![chart1.PNG](/scrnShots/chart1.PNG)

![chart2.PNG](/scrnShots/chart2.PNG)

![chart3.PNG](/scrnShots/chart3.PNG)

![chart4.PNG](/scrnShots/chart4.PNG)

## **How to run**  

(1) Run zookeeper , kafka servers , HDFS , Hive and Impala Services.

(2) Run Twitter-Kafka-Producer  to produce data (tweets) in JSON Format to Kafka topic.

(3) Run Spark-Streaming to write data filtered and cleaned to HDFS in Parquet files.

(4) Create Hive table in specified directory which is the same spark writeStream in.

(5) Install ODBC connector to connect from tableau to Cloudera Hadoop to make some visuals.





