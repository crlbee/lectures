#!/bin/bash
APP_HOME='.'
java -Xmx256m -Xms50m \
	-XX:+UseG1GC -XX:G1HeapRegionSize=1 \
	-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath="$APP_HOME/dumps"\
	-Xloggc:"$APP_HOME/logs/`date +%F_%H-%M-%S`-gc.log" -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCCause\
	-XX:+UnlockCommercialFeatures -XX:+FlightRecorder\
	-Dcom.sun.management.jmxremote.port=50199\
	-Dcom.sun.management.jmxremote.authenticate=false\
	-Dcom.sun.management.jmxremote.ssl=false\
	-Xrunjdwp:transport=dt_socket,address=50100,server=y,suspend=n\
	-jar "$APP_HOME/target/tooling-0.0.1-SNAPSHOT-jar-with-dependencies.jar"
