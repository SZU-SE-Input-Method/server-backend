#!/bin/sh
echo =================================
echo  start update server-backend
echo =================================

echo pull~~
cd /usr/local/src/server-backend/deployment
git pull
echo pull compelete

echo update
name=server-backend 
#判断是否容器存在
docker ps -a | grep $name &> /dev/null
#如果存在，关闭并删除该容器
if [ $? -eq 0 ]
then
    echo $name" is up,we will stop and remove it!!!"
    docker stop $name 
    docker rm $name 
else
    echo $name" is not up!!!"
fi
cd /usr/local/src/server-backend/deployment
docker-compose up -d --build
echo update complete
