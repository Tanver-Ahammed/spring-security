# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Accept the build argument for the commit hash
ARG COMMIT_HASH

# Copy the Maven build output (JAR file)
COPY target/spring-security-0.0.1-SNAPSHOT.jar spring-security.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "spring-security.jar"]
