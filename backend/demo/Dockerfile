# Usa una imagen base de Java
FROM openjdk:11-jre-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR de tu aplicación (asegúrate de que el nombre sea correcto)
COPY demo/target/demo-0.0.1-SNAPSHOT.jar app.jar 

# Expone el puerto que usará tu aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]