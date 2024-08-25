# Use the official Maven image to build the project and a JDK image to run the application
# Stage 1: Build
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/location-api-0.0.1-SNAPSHOT.jar location-service.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "location-service.jar"]
