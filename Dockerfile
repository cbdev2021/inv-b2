FROM adoptopenjdk:8-jre-hotspot

WORKDIR /app

COPY target/your-application.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
