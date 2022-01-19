#!/bin/bash

cmd=$(ps aux | grep "singte-web" | grep java | awk '{print $2}')
echo -e "PID: \n\033[31m\033[05m$cmd\033[0m"
echo "PID:$cmd"
for id in ${cmd}; do
  kill -9 ${id}
  echo "kill $id"
done
echo 'finish kill'

cd ../../
nohup java -jar singte-web.jar --spring.profiles.active=prod 2>&1 &