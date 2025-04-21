FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
ADD target/weather-api-images.jar weather-api-images.jar
ENTRYPOINT ["java", "-jar", "weather-api-images.jar"]