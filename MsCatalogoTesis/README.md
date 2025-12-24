# MsCatalogoTesis - Microservicio de Catálogo

Microservicio Spring Boot para gestión de catálogos con MongoDB.

## 📋 Requisitos

- Java 25
- MongoDB 4.0+
- Maven 3.6+

## 🚀 Configuración

### 1. Configurar MongoDB

Asegúrate de que MongoDB esté corriendo en `localhost:27017` o actualiza la configuración en `application.properties`.

### 2. Inicializar el Catálogo

Ejecuta el script de inicialización en MongoDB Compass o Mongosh:

```bash
mongosh < src/main/resources/scripts/init-catalogo.js
```

O cópialo y pégalo directamente en MongoDB Compass (\_MONGOSH).

### 3. Ejecutar la Aplicación

```bash
./mvnw spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8083`

### 4. Acceder a Swagger UI

Una vez iniciada la aplicación, accede a la documentación interactiva:

```
http://localhost:8083/swagger-ui.html
```

## 📚 API Endpoints

### Obtener todo el catálogo

```http
GET /api/catalogo
```

### Obtener elementos por grupo

```http
GET /api/catalogo/grupo/{grupo}
```

Ejemplos:

- `/api/catalogo/grupo/ESTADO_PROYECTO`
- `/api/catalogo/grupo/ROL_JURADO`
- `/api/catalogo/grupo/ESPECIALIDAD`

### Obtener solo elementos activos de un grupo

```http
GET /api/catalogo/grupo/{grupo}/activos
```

### Obtener un elemento específico

```http
GET /api/catalogo/grupo/{grupo}/codigo/{codigo}
```

Ejemplo:

- `/api/catalogo/grupo/ESTADO_PROYECTO/codigo/PENDIENTE`

### Crear nuevo elemento

```http
POST /api/catalogo
Content-Type: application/json

{
  "grupo": "ESTADO_PROYECTO",
  "codigo": "NUEVO_ESTADO",
  "nombre": "Nuevo Estado",
  "activo": true,
  "orden": 7
}
```

### Actualizar elemento

```http
PUT /api/catalogo/{id}
Content-Type: application/json

{
  "grupo": "ESTADO_PROYECTO",
  "codigo": "PENDIENTE",
  "nombre": "Pendiente (Actualizado)",
  "activo": true,
  "orden": 1
}
```

### Eliminar elemento

```http
DELETE /api/catalogo/{id}
```

## 📊 Grupos de Catálogo Disponibles

1. **ESTADO_PROYECTO**: Estados del proyecto de tesis

   - PENDIENTE
   - REVISION_FORMATO
   - EN_ASESOR
   - LISTO_SORTEO
   - EN_JURADO
   - APROBADO

2. **ESTADO_BORRADOR**: Estados del borrador de tesis

   - REVISION_FORMATO
   - EN_JURADO
   - DICTAMINACION
   - FINALIZADO

3. **ROL_JURADO**: Roles del jurado

   - PRESIDENTE
   - SECRETARIO
   - VOCAL

4. **DOCENTE_CATEGORIA**: Categoría del docente

   - NOMBRADO
   - CONTRATADO

5. **DOCENTE_GRADO**: Grado académico

   - MAESTRO
   - DOCTOR

6. **REVISION_FASE**: Fase de revisión

   - PROYECTO
   - BORRADOR

7. **DICTAMEN_ESTADO**: Estado del dictamen

   - APROBADO
   - OBSERVADO
   - RECHAZADO

8. **SUSTENTA_MODALIDAD**: Modalidad de sustentación

   - PRESENCIAL
   - VIRTUAL
   - MIXTA

9. **SUSTENTA_RESULTADO**: Resultado de sustentación

   - APROBADO_UNANIMIDAD
   - APROBADO_MAYORIA
   - DESAPROBADO

10. **ESPECIALIDAD**: Especialidades disponibles
    - GESTION_TECNOLOGIAS_INFORMACION
    - REDES_TELECOMUNICACIONES
    - INGENIERIA_SOFTWARE
    - CIENCIAS_COMPUTACION

## 🗄️ Estructura de Datos

```json
{
  "id": "507f1f77bcf86cd799439011",
  "grupo": "ESTADO_PROYECTO",
  "codigo": "PENDIENTE",
  "nombre": "PENDIENTE",
  "activo": true,
  "orden": 1,
  "createdAt": "2025-12-23T18:00:00",
  "updatedAt": "2025-12-23T18:00:00"
}
```

## 🔍 Índices MongoDB

1. **uq_grupo_codigo**: Índice único en `grupo` + `codigo`
2. **idx_grupo_activo_orden**: Índice compuesto para consultas por grupo
3. **idx_updatedAt_desc**: Índice descendente en `updatedAt`

## 🛠️ Estructura del Proyecto

```
src/main/java/com/microservice/MsCatalogoTesis/
├── config/
│   ├── GlobalExceptionHandler.java  # Manejo global de excepciones
│   ├── ModelMapperConfig.java       # Configuración de ModelMapper
│   ├── MongoConfig.java             # Configuración MongoDB y auditing
│   └── OpenApiConfig.java           # Configuración Swagger/OpenAPI
├── controller/
│   └── CatalogoController.java      # REST API endpoints
├── model/
│   ├── Catalogo.java                # Modelo de datos MongoDB
│   └── error/
│       ├── BusinessRuleException.java      # Excepción de reglas de negocio
│       ├── ErrorResponse.java              # Respuesta de error estandarizada
│       └── ResourceNotFoundException.java  # Excepción de recurso no encontrado
├── repository/
│   └── CatalogoRepository.java      # Repositorio MongoDB
├── service/
│   └── CatalogoService.java         # Lógica de negocio
└── util/
    ├── ApiRoutes.java               # Constantes de rutas
    └── ResponseBase.java            # Respuestas estandarizadas

src/main/resources/
├── application.properties           # Configuración de la aplicación
└── scripts/
    └── init-catalogo.js            # Script de inicialización MongoDB
```

## ⚙️ Características Implementadas

### 🔧 Config

- **GlobalExceptionHandler**: Manejo centralizado de excepciones con respuestas HTTP apropiadas
- **MongoConfig**: Configuración de MongoDB con auditing automático
- **OpenApiConfig**: Configuración de Swagger/OpenAPI para documentación interactiva
- **ModelMapperConfig**: Bean de ModelMapper para mapeo de objetos

### ❌ Error Handling

- **ErrorResponse**: Clase para respuestas de error estandarizadas
- **ResourceNotFoundException**: Excepción para recursos no encontrados (404)
- **BusinessRuleException**: Excepción para violaciones de reglas de negocio (400)
- Manejo de validaciones con `@Valid`
- Manejo de excepciones genéricas (500)

### 📖 Swagger/OpenAPI

- Documentación interactiva en `/swagger-ui.html`
- API Docs JSON en `/api-docs`
- Operaciones ordenadas por método HTTP
- Información completa del API

### 📝 Logging

- Logs en archivo: `logs/ms_catalogo_tesis.log`
- Niveles configurables (INFO, DEBUG, WARN, ERROR)
- Logging de MongoDB queries en DEBUG
- Logging detallado de la aplicación

### 🛠️ Utilities

- **ResponseBase**: Clase genérica para respuestas estandarizadas del API
- **ApiRoutes**: Constantes centralizadas de rutas para mejor mantenibilidad

## 📋 Configuración (application.properties)

```properties
# Aplicación
spring.application.name=MsCatalogoTesis
server.port=8083

# MongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/ms_catalogo_tesis
spring.data.mongodb.database=ms_catalogo_tesis
spring.data.mongodb.auto-index-creation=true

# Swagger
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method

# Logging
logging.file.name=logs/ms_catalogo_tesis.log
logging.level.root=INFO
logging.level.org.springframework.data.mongodb=DEBUG
logging.level.com.microservice.MsCatalogoTesis=DEBUG
```

## 🧪 Testing con cURL

```bash
# Listar todos los catálogos
curl http://localhost:8083/api/catalogo

# Obtener estados de proyecto
curl http://localhost:8083/api/catalogo/grupo/ESTADO_PROYECTO

# Obtener solo activos
curl http://localhost:8083/api/catalogo/grupo/ROL_JURADO/activos

# Obtener específico
curl http://localhost:8083/api/catalogo/grupo/ESPECIALIDAD/codigo/INGENIERIA_SOFTWARE

# Crear nuevo
curl -X POST http://localhost:8083/api/catalogo \
  -H "Content-Type: application/json" \
  -d '{
    "grupo": "TEST",
    "codigo": "VALOR1",
    "nombre": "Valor de Prueba",
    "activo": true,
    "orden": 1
  }'
```

## 📝 Notas

- Los campos `createdAt` y `updatedAt` se gestionan automáticamente con MongoDB Auditing
- El script de inicialización usa **UPSERT** para evitar duplicados
- El índice único en `grupo+codigo` previene registros duplicados
- Todas las excepciones se manejan de forma centralizada
- Los logs se guardan automáticamente en el directorio `logs/`
- Swagger UI permite probar todos los endpoints sin necesidad de herramientas externas
