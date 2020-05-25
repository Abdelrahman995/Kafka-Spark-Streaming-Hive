# Kafka-Spark-Streaming-Hive



## Hive 

First make HDFS directory 

IF you got **Name node is in safe mode**!. Then try :
`'sudo -u hdfs hdfs dfsadmin -safemode leave'`

Then create Directory :
`'sudo -u hdfs hadoop fs -mkdir -p /user/hive/warehouse/twitterDB' `

Also we need to change hdfs owner to cloudera run this command :
`'sudo -u hdfs hadoop fs -chown cloudera /user/hive/warehouse/twitterDB'`
