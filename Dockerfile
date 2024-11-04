FROM eclipse-temurin:23-jdk-alpine
VOLUME /tmp
WORKDIR /app
COPY target/shipboot-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
