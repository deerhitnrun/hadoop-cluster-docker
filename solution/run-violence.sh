#!/bin/bash

# test the hadoop cluster by running wordcount

# create input directory on HDFS
hadoop fs -mkdir -p input

# put input files to HDFS
echo -e "Uncompressing database..."
unzip input/globalterrorismdb_0616dist.csv.zip
rm globalterrorismdb_0616dist.csv.zip
echo -e "\nUploading input to HDFS..."
hdfs dfs -put ./input/* input
echo -e "\nInput uploaded to HDFS:"
hdfs dfs -ls input

# run Killings 
export LIBJARS=$HOME/libs/commons-csv-1.4.jar
hadoop jar ./main/violence-1.0-SNAPSHOT.jar com.hugeinc.hadoop.examples.violence.KillingsDriver -libjars ${LIBJARS} input output

# print the output of wordcount
echo -e "\nViolence DB output:"
hdfs dfs -cat output/part-r-00000