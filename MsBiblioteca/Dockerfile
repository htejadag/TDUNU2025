# -------------------------------------------------------------------
# ETAPA 1: BUILD (Construcción)
# Usamos 'eclipse-temurin' en lugar de 'openjdk' porque este último fue eliminado.
# -------------------------------------------------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS build
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
# Usamos la imagen ligera de 'eclipse-temurin' para correr la app.
# -------------------------------------------------------------------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos el .jar
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]