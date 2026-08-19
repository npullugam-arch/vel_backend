# ==============================
# BUILD STAGE
# ==============================
FROM maven:3.9-eclipse-temurin-23 AS build

WORKDIR /app

# Copy Maven configuration first
COPY pom.xml .

# Copy source code
COPY src ./src

# Build Spring Boot application
RUN mvn clean package -DskipTests


# ==============================
# RUN STAGE
# ==============================
FROM eclipse-temurin:23-jre

WORKDIR /app

# Copy generated JAR
COPY --from=build /app/target/*.jar app.jar

# Render/Spring Boot port
EXPOSE 8080

# Limit JVM memory for Render
CMD ["java", "-Xms64m", "-Xmx256m", "-XX:+UseSerialGC", "-jar", "app.jar"]