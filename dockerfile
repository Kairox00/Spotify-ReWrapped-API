# Start with a lightweight Java runtime environment
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot jar file into the container
COPY target/your-application.jar app.jar

# Expose the port your application runs on (default is 8080)
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]
