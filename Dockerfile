#---------------------stage1-------------------

from maven:3.8.3-openjdk-17 AS builder

workdir /app

copy . /app

run mvn clean install -DskipTests=true

#-----------------stage2-------------------------
from openjdk:17-alpine

workdir /app

copy --from=builder /app/target/*.jar /app/target/bankapp.jar


expose 8080

ENTRYPOINT ["java","-jar","/app/target/bankapp.jar"]

