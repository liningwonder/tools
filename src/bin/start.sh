#!/bin/bash

# shellcheck disable=SC2046
# current script path

#获取脚本文件的绝对路径
BIN_PATH=$(cd `dirname $0`; pwd)
cd ${BIN_PATH}

cd ../

#脚本文件的父路径
APP_HOME_PATH=$(pwd)

cd ${BIN_PATH}


CONF_PATH=${APP_HOME_PATH}/conf
LOG4J_CONFIG_FILE=${CONF_PATH}/log4j2.xml


if [ -f ${APP_HOME_PATH}/bin/app.pid ] ; then
	echo "found app.pid , Please run stop.sh first ,then start.sh" 2>&2
  exit 1
fi

if [ ! -d ${APP_HOME_PATH}/log ] ; then
	mkdir -p ${APP_HOME_PATH}/log
fi

START_LOG=${APP_HOME_PATH}/log/start.log

if [ ! -f $START_LOG ] ; then
	echo "" > $START_LOG
fi

JAVA_HOME=/usr/jdk1.8.0_221
JAVA=$JAVA_HOME/bin/java
JAVA_OPTS="-server -Xms2048m -Xmx2048m"
JAVA_OPTS="${JAVA_OPTS} -XX:+HeapDumpOnOutOfMemoryError"
JAVA_OPTS="${JAVA_OPTS} -Dfile.encoding=UTF-8"
JAVA_OPTS="${JAVA_OPTS} -Dlog4j.configurationFile=${LOG4J_CONFIG_FILE}"

for i in ${APP_HOME_PATH}/lib/*;
    do CLASSPATH=$i:"$CLASSPATH";
done

CLASSPATH="${APP_HOME_PATH}/conf:$CLASSPATH";

cd $bin_abs_path

${JAVA} ${JAVA_OPTS} -classpath .:$CLASSPATH com.lining.log.Main >>$START_LOG 2>&1 &

echo $! > ${APP_HOME_PATH}/bin/app.pid
