# Use a Maven image to build the project
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy the project files into the container
COPY . .

# Build the project (Maven command)
RUN mvn clean package -DskipTests

# Use a smaller base image for running the Spring Boot app
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/api-backbone-app.jar /app/api-backbone-app.jar

# Expose the port the app runs on
EXPOSE 8080

# Set the entry point to run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/api-backbone-app.jar"]
