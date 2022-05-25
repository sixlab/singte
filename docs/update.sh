#!/bin/bash

cd ../
git pull
/usr/local/maven/bin/mvn clean install -Dmaven.test.skip=true

mv -f ./singte/singte-web/target/singte-web.jar ./
