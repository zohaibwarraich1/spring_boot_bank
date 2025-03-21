FROM maven:eclipse-temurin AS builder
WORKDIR /app
COPY . ./
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target /app/target
CMD [ "java", "-jar", "target/bankapp-0.0.1-SNAPSHOT.jar" ]
