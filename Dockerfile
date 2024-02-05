# Fase de construcción
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app
COPY . .

RUN mvn clean package

# Fase de ejecución
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=build /app/target/Inv-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
