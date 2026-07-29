# Stage 1: Build the Monolith (Frontend + Backend)
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app
# Copy the entire monolithic workspace
COPY . .

# Run the maven build inside the backend directory (this also builds Angular)
WORKDIR /app
RUN echo "=== CONTENTS OF /app ===" && ls -la && echo "=== CONTENTS OF /app/backend (if exists) ===" && ls -la backend || true

WORKDIR /app/backend
RUN mvn clean package -DskipTests

# Stage 2: Run the unified application (Python + Java)
FROM python:3.13-slim

# Install OpenJDK 21
RUN apt-get update && \
    apt-get install -y openjdk-21-jre-headless && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/backend/target/*.jar app.jar

# Copy the genai_service Python app from the build stage
COPY --from=build /app/genai_service ./genai_service

# Install Python requirements
WORKDIR /app/genai_service
RUN pip install --no-cache-dir -r requirements.txt

# Go back to root app dir
WORKDIR /app

# Expose the single unified port (8085) for the outside world
EXPOSE 8085

# Run the Spring Boot JAR (which in turn launches the Python server internally)
ENTRYPOINT ["java", "-jar", "app.jar"]
