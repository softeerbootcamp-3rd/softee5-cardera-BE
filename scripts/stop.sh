#!/bin/bash
echo "run stop.sh"

ROOT_PATH="/home/ubuntu/cardera"
JAR="$ROOT_PATH/cardera.jar"

STOP_LOG="$ROOT_PATH/stop.log"
SERVICE_PID=$(pgrep -f $JAR) # 실행중인 Spring 서버의 PID

if [ -z "$SERVICE_PID" ]; then
  echo "서비스 NouFound" >> $STOP_LOG
else
  echo "서비스 종료 " >> $STOP_LOG
  kill -9 "$SERVICE_PID"
fi