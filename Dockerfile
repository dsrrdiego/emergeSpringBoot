# Usa una imagen de OpenJDK 17 como base
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/emergespring-0.0.1-SNAPSHOT.jar dock.jar
COPY . /app

EXPOSE 8080

CMD ["java", "-jar", "dock.jar"]



#//////
# sudo docker build -t dock.jar .
# sudo docker run -d -p 8080:8080 dock.jar
# docker ps muestra
# docker log id
# docker stop id
 
