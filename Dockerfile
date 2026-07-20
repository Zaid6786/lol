FROM openjdk:17-jdk-slim
WORKDIR /app
COPY certo3.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
