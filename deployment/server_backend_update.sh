#!/bin/sh
echo =================================
echo  start update server-backend
echo =================================

echo pull~~
cd /usr/local/src/server-backend
git pull
echo pull compelete

echo update
docker stop server-backend
docker rm server-backend
docker-compose up -d --build
echo update complete
