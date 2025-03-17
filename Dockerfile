# Build Stage
FROM maven:3.8.5-openjdk-18 AS builder
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime Stage
FROM openjdk:18-alpine
WORKDIR /app
COPY --from=builder /app/target/app*.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]

