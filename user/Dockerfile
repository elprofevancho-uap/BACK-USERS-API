# Usa una imagen oficial de Java (ajusta la versión de Java según tu proyecto)
FROM eclipse-temurin:21-jdk-alpine

# Define el volumen temporal
VOLUME /tmp

# Copia el archivo .jar generado por tu proyecto al contenedor
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java","-jar","/app.jar"]
