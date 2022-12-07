#!/bin/bash

cd ../
git pull
mvn clean install -Dmaven.test.skip=true

cmd=$(ps aux | grep "singte-web" | grep java | awk '{print $2}')
echo -e "PID: \n\033[31m\033[05m$cmd\033[0m"
echo "PID:$cmd"
for id in ${cmd}; do
  kill -9 ${id}
  echo "kill $id"
done
echo 'finish kill'

cd ../
rm singte-web.jar
mv ./singte/singte-web/target/singte-web.jar ./
nohup java -jar singte-web.jar --spring.profiles.active=prod 2>&1 &