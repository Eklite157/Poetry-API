# Image
FROM eclipse-temurin:17-jre

# Set working directory inside container
WORKDIR /app

# Copy the built jar into the container
COPY target/Poetry-API-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]