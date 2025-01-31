FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /target/*.jar app.jar
EXPOSE 3000
CMD ["java", "-jar", "app.jar"]
