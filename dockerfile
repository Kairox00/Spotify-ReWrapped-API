FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/rewrapped-api-0.0.0.jar app.jar
EXPOSE 3000
CMD ["java", "-jar", "app.jar"]
