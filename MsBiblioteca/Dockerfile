# -------------------------------------------------------------------
# ETAPA 1: BUILD (Construcción)
# Usamos una imagen de Maven con Java 25
# -------------------------------------------------------------------
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# 1. Copiamos pom.xml y descargamos dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# 2. Copiamos el código fuente
COPY src ./src

# 3. Compilamos (saltando tests para rapidez)
RUN mvn clean package -DskipTests

# -------------------------------------------------------------------
# ETAPA 2: RUN (Ejecución)
# Usamos la imagen ligera (JRE) versión 25 para correr el WAR
# -------------------------------------------------------------------
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiamos el .war generado en la etapa anterior
COPY --from=build /app/target/*.war app.war

# Exponemos el puerto
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.war"]