FROM openjdk:8
# author
MAINTAINER LiuYe

VOLUME /usr/local/src/server-backend

RUN mkdir -p /home/server-backend

WORKDIR /home/server-backend

COPY ./sedemo-0.0.1-SNAPSHOT.jar /home/server-backend

ENTRYPOINT ["java","-jar","/home/server-backend/sedemo-0.0.1-SNAPSHOT.jar"]