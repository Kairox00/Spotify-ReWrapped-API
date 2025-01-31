FROM openjdk:21-jdk-slim AS build
RUN apt-get update && apt-get install -y maven
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 3000
CMD ["java", "-jar", "app.jar"]
