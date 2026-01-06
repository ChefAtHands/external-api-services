FROM eclipse-temurin:19-jre-alpine
WORKDIR /app
COPY target/recipe-search-service-0.1.0-SNAPSHOT.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]