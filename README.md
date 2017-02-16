##Run Hadoop Cluster within Docker Containers

- Blog: [Run Hadoop Cluster in Docker Update](http://kiwenlau.com/2016/06/26/hadoop-cluster-docker-update-english/)
- 博客: [基于Docker搭建Hadoop集群之升级版](http://kiwenlau.com/2016/06/12/160612-hadoop-cluster-docker-update/)


![alt tag](https://raw.githubusercontent.com/kiwenlau/hadoop-cluster-docker/master/hadoop-cluster-docker.png)

### Prerrequisites

- Docker (Tested on Docker 1.13.1 for MacOS)
- Gradle (in case you want to tinker with the Java MapReduce code)

### Start Hadoop Cluster

#####1. Build image for Docker with everything required by Hadoop

```
./build_hadoop_image.sh
```

#####2. Start the cluster nodes

```
cd hadoop-cluster-docker
docker-compose up -d
```
It will start 3 containers, 1 of them as master and 2 as slaves.

You should get something like this:

```
start hadoop-master container...
start hadoop-slave1 container...
start hadoop-slave2 container...
```

#####3. Open the Master node's shell

```
docker exec -it hadoop-master bash
```

You should get something like this:

```
root@hadoop-master:~# 
```

This will put you in the /root directory of the hadoop-master container

#####4. Start Hadoop from the Master node

```
./start-hadoop.sh
```

This will allow Hadoop to start up in the Master and acknowledge the Hadoop slaves.

#####5. Run the Violence DB solution

```
./run-violence.sh
```

### Build the MapReduce code

```
gradle build
```

### Test the MapREduce code

```
gradle test
```
