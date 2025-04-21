FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/weather-0.0.1-SNAPSHOT.jar weather-app.jar
ENTRYPOINT ["java", "-jar", "weather-app.jar"]