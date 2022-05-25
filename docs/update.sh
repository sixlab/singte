#!/bin/bash

cd ../
git pull
mvn clean install -Dmaven.test.skip=true

cd ../
mv -f ./singte/singte-web/target/singte-web.jar ./
