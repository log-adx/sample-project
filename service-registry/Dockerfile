# Use the official JDK image from Docker Hub
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file from target directory to container
COPY target/service-registry-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app will run on
EXPOSE 8761

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]