# ---- Stage 1: Build the application ----
FROM maven:3.9-amazoncorretto-17 AS build

# Set working directory
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# ---- Stage 2: Runtime with Amazon Corretto ----
FROM amazoncorretto:17-alpine-jdk

# Set working directory
WORKDIR /app

# Accept commit hash as build argument and environment variable
ARG COMMIT_HASH
ENV COMMIT_HASH=${COMMIT_HASH}

# Copy JAR from build stage
COPY --from=build /app/target/spring-security-0.0.1-SNAPSHOT.jar spring-security.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "spring-security.jar"]
