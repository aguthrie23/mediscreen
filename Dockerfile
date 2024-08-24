# Use an official Java image as a base
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application jar file to the working directory
COPY target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Entry point to start the application
ENTRYPOINT ["java", "-jar", "app.jar"]