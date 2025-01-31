FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/rewrapped-0.0.1.jar app.jar
EXPOSE 3000
CMD ["java", "-jar", "app.jar"]
