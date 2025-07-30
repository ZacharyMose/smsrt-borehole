# ---------- Build Stage ----------
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copy all project files
COPY . .

# Build the Spring Boot project and skip tests (optional)
RUN mvn clean package -DskipTests

# ---------- Runtime Stage ----------
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy the built JAR from the previous build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port (Render will override this dynamically)
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "app.jar"]
