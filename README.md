## standard maven assembly project


### start

bin/start.sh


### stop

bin/stop.sh



### Spring Boot start script



```shell
#!/bin/bash

# shellcheck disable=SC2046
# current script path

#获取脚本文件的绝对路径
BIN_PATH=$(cd `dirname $0`; pwd)
cd ${BIN_PATH}

APP_HOME_PATH=$(pwd)

echo ${APP_HOME_PATH}


if [ -f ${APP_HOME_PATH}/app.pid ] ; then
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


CONF_PATH=${APP_HOME_PATH}/application-pro.properties
LOG4J_CONFIG_FILE=${APP_HOME_PATH}/log4j2.xml

JAVA_HOME=/APBIZAS/iam/jdk1.8.0_221
JAVA=$JAVA_HOME/bin/java
JAVA_OPTS="-server -Xms1024m -Xmx1024m"
JAVA_OPTS="${JAVA_OPTS} -XX:+HeapDumpOnOutOfMemoryError"
JAVA_OPTS="${JAVA_OPTS} -Dfile.encoding=UTF-8"
JAVA_OPTS="${JAVA_OPTS} -Dlog4j.configurationFile=${LOG4J_CONFIG_FILE}"

${JAVA} ${JAVA_OPTS} -jar app.jar --spring.profiles.active=pro --spring.config.location=${CONF_PATH} >>$START_LOG 2>&1 &


echo $! > ${APP_HOME_PATH}/app.pid

```

