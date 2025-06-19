# Sets image base for Docker container
FROM eclipse-temurin:17-jdk

# Set working directory inside container
WORKDIR /app

# Copy scripts and pom.xml first
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy source code
COPY src ./src

# Give execute permission to Maven wrapper
RUN chmod +x mvnw

# Build the jar inside Docker container
RUN ./mvnw clean package -DskipTests

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the built jar
CMD ["java", "-jar", "target/Poetry-API-0.0.1-SNAPSHOT.jar"]
