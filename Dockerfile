# Use a base image that includes a version of Java (e.g., openjdk)
FROM openjdk:11

# Set the working directory inside the container (you can customize this)
WORKDIR /hotel-booking-service

# Copy your Java application JAR file to the container (update the file name)
COPY /target/hotel-booking-service-0.0.1-SNAPSHOT.jar /hotel-booking-service

# Expose port
EXPOSE 8081/tcp
# Define the entry point for running your Java application
CMD ["java", "-jar", "hotel-booking-service-0.0.1-SNAPSHOT.jar"]
