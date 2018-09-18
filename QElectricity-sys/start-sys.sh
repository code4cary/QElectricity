#!/bin/bash

#author weijohn

#打包
cd ~/QElectricity/QElectricity-sys
mvn clean 
mvn package


#拷贝charge-common模块jar文件
cd ~/QElectricity/QElectricity-sys/charge-common/target  
cp charge-common-1.0-SNAPSHOT.jar ~/QElectricity/QElectricity-sys/charge-web/target

#拷贝charge-dao模块jar文件
cd ~/QElectricity/QElectricity-sys/charge-dao/target  
cp charge-dao-1.0-SNAPSHOT.jar ~/QElectricity/QElectricity-sys/charge-web/target

#拷贝charge-entity模块jar文件
cd ~/QElectricity/QElectricity-sys/charge-entity/target  
cp charge-entity-1.0-SNAPSHOT.jar ~/QElectricity/QElectricity-sys/charge-web/target

#拷贝charge-service模块jar文件
cd ~/QElectricity/QElectricity-sys/charge-service/target  
cp charge-service-1.0-SNAPSHOT.jar ~/QElectricity/QElectricity-sys/charge-web/target

#docker-compose启动项目
cd ~/QElectricity/QElectricity-sys
docker-compose up 

