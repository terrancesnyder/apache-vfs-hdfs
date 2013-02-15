apache-vfs-hdfs
===============

HDFS clone from Pentaho that updates HDFS to Hadoop 1.0.4 and Apache VFS 2.0

## Hadoop Version Support

By Default the project will build a compliant version that supports both Hadoop 1.0.4 and Cloudera Hadoop 2.0.

### Hadoop 1.0.4 *(Apache)*

+ hadoop-core-1.0.4.jar
+ commons-configuration-1.6.jar
+ commons-vfs2-2.0.jar

### Hadoop 2.0.0 *(Cloudera)*

+ hadoop-annotations-2.0.0-cdh4.1.2.jar
+ hadoop-auth-2.0.0-cdh4.1.2.jar
+ hadoop-common-2.0.0-cdh4.1.2.jar
+ hadoop-hdfs-2.0.0-cdh4.1.2.jar
+ commons-configuration-1.6.jar
+ commons-vfs2-2.0.jar
+ protobuf-java-2.4.0a.jar

### Ivy/Maven Repositories

+ https://repository.cloudera.com/artifactory/cloudera-repos
+ http://download.java.net/maven/2/

## How To Compile

	git checkout
	[update ivy.xml with Hadoop 1.0.4 OR Hadoop 2.0.0]
	./ant clean resolve dist