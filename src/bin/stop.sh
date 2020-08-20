#!/bin/bash

# shellcheck disable=SC2046
# current script path

BIN_PATH=$(cd `dirname $0`; pwd)
cd ${BIN_PATH}

cd ../

APP_HOME_PATH=$(pwd)
cd ${BIN_PATH}


PID_FILE=${BIN_PATH}/app.pid

if [ ! -f "${PID_FILE}" ];then
	echo "pid file not exists"
	exit
fi

pid=`cat ${PID_FILE}`

if [ "$pid" == "" ] ; then
	pid="can not get pid, please stop it manually"
	exit
fi

kill $pid