#!/bin/bash

# test the hadoop cluster by running wordcount

# create input directory on HDFS
hadoop fs -mkdir -p input

# put input files to HDFS
echo -e "\nuploading input to HDFS:"
hdfs dfs -put ./input/* input
echo -e "\ninput uploaded to HDFS:"
hdfs dfs -ls input

# run Killings 
export LIBJARS=$HOME/libs/commons-csv-1.4.jar
hadoop jar ./main/violence-1.0-SNAPSHOT.jar com.hugeinc.hadoop.examples.violence.KillingsDriver -libjars ${LIBJARS} input output

# print the output of wordcount
echo -e "\nviolence output:"
hdfs dfs -cat output/part-r-00000