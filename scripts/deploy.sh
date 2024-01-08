#!/bin/bash
echo "run deploy.sh"

ROOT_PATH="/home/ubuntu/cardera"
JAR="$ROOT_PATH/cardera.jar"

APP_LOG="$ROOT_PATH/application.log"
START_LOG="$ROOT_PATH/start.log"

NOW=$(date +%c)

echo "[$NOW] $JAR 복사" >> $START_LOG
cp $ROOT_PATH/build/libs/cardera-0.0.1-SNAPSHOT.jar $JAR

echo "[$NOW] > $JAR 실행" >> $START_LOG
nohup java -jar $JAR --spring.profiles.active=prod > /dev/null 2>&1 &

SERVICE_PID=$(pgrep -f $JAR)
echo "[$NOW] > 서비스 PID: $SERVICE_PID" >> $START_LOG