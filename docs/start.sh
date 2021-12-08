#!/bin/bash

url='127.0.0.1:3306/singte'
pwd='123456'

nohup java -jar singte-web.jar --st.db.jdbc=${url} --st.db.pwd=${pwd} 2>&1 &
