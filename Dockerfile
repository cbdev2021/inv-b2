FROM adoptopenjdk:8-jre-hotspot

WORKDIR /app

# COPY target/your-application.jar app.jar
COPY target/Inv-0.0.1-SNAPSHOT.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]
