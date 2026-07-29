# Stage 1: Build the Monolith (Frontend + Backend)
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app
# Copy whatever is in the context
COPY . .

# Auto-heal the context if the user ran Docker from inside the backend folder
RUN if [ -f "pom.xml" ]; then \
        echo "Detected backend/ context. Healing directory structure..."; \
        mkdir -p /temp-workspace/backend; \
        cp -r * /temp-workspace/backend/ || true; \
        git clone https://github.com/Zaid6786/lol.git /tmp/repo; \
        cp -r /tmp/repo/frontend /temp-workspace/; \
        cp -r /tmp/repo/genai_service /temp-workspace/; \
        rm -rf /app/*; \
        cp -r /temp-workspace/* /app/; \
        rm -rf /temp-workspace /tmp/repo; \
    fi

WORKDIR /app/backend
RUN mvn clean package -DskipTests

# Stage 2: Run the unified application (Python + Java)
FROM python:3.13-slim

# Copy OpenJDK 21 directly from the official image to avoid apt-get disk space issues
COPY --from=eclipse-temurin:21-jre /opt/java/openjdk /opt/java/openjdk
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"

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
