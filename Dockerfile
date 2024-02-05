# Usa la imagen de OpenJDK 17 como base
FROM openjdk:17-jre-slim


# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR construido por Maven a la imagen
COPY target/Inv-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "app.jar"]
