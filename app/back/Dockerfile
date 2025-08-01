# Usar una imagen base de OpenJDK 17 con Alpine Linux para un contenedor más ligero
FROM openjdk:17-jdk-alpine

# Instalar bash (opcional, útil para debugging)
RUN apk add --no-cache bash

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos de configuración de Gradle
COPY app/back/build.gradle .
COPY app/back/settings.gradle .
COPY app/back/gradle/ gradle/
COPY app/back/gradlew .
COPY app/back/gradlew.bat .

# Dar permisos de ejecución al wrapper de Gradle
RUN chmod +x ./gradlew

# Descargar las dependencias (esto se cachea si no cambian los archivos de configuración)
RUN ./gradlew dependencies --no-daemon

# Copiar el código fuente
COPY app/back/src/ src/

# Construir la aplicación
RUN ./gradlew build -x test --no-daemon

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Definir variables de entorno para la base de datos
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/usuarios_bd
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=123
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "build/libs/back-0.0.1-SNAPSHOT.jar"]