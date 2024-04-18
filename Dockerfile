# Usa una imagen de OpenJDK 17 como base
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo en /app
WORKDIR /app

COPY emergeSpringBoot/ /app
# Copia el archivo JAR de tu aplicación en el contenedor
COPY emergeSpringBoot/target/emergespring-0.0.1-SNAPSHOT.jar dock.jar
# Expone el puerto 8080 para que la aplicación esté disponible desde fuera del contenedor
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot cuando se inicie el contenedor
CMD ["java", "-jar", "dock.jar"]



#//////
# sudo docker build -t dock.jar .
# sudo docker run -d -p 8080:8080 dock.jar
# docker ps muestra
# docker log id
# docker stop id
 
