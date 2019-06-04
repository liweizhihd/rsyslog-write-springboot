#/bin/bash
PWDPATH=`dirname $0`
PORTAL_HOME=`cd $PWDPATH && cd .. && pwd`
echo $PORTAL_HOME
cd $PORTAL_HOME
JVM_OPTS="
-server
 -Xms1g
 -Xmx2g
 -XX:NewSize=512m
 -XX:SurvivorRatio=6
 -XX:+AlwaysPreTouch
 -XX:+UseG1GC
 -XX:MaxGCPauseMillis=2000
 -XX:GCTimeRatio=4
 -XX:InitiatingHeapOccupancyPercent=30
 -XX:G1HeapRegionSize=8M
 -XX:ConcGCThreads=2
 -XX:G1HeapWastePercent=10
 -XX:+UseTLAB
 -XX:+ScavengeBeforeFullGC
 -XX:+DisableExplicitGC
 -XX:+PrintGCDetails
 -XX:-UseGCOverheadLimit
 -XX:+PrintGCDateStamps
 -Xloggc:logs/gc.log
"

start() {
nohup java $JVM_OPTS -jar lib/rsyslog-0.0.1-SNAPSHOT.jar &
echo -e '\r'
}

start >> logs/console.log 2>> logs/console.log