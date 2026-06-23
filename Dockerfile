FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app
COPY target/tourism-service.jar /app/tourism-service.jar

ENTRYPOINT ["java", "-jar", "tourism-service.jar"]