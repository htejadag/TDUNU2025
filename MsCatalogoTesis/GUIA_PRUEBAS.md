# 🧪 Guía de Prueba - MsCatalogoTesis

Esta guía te ayudará a probar el microservicio paso a paso.

## 📋 Pre-requisitos

### 1. Verificar que MongoDB esté instalado y corriendo

#### Opción A: MongoDB instalado localmente

```bash
# Verificar si MongoDB está corriendo
# En Windows, busca el servicio "MongoDB" en Servicios
# O ejecuta:
net start MongoDB
```

#### Opción B: MongoDB con Docker

```bash
docker run -d -p 27017:27017 --name mongodb mongo:latest
```

#### Verificar conexión

```bash
# Deberías poder conectarte a MongoDB en localhost:27017
# Puedes usar MongoDB Compass para verificar la conexión
```

---

## 🚀 Pasos para Probar

### **PASO 1: Inicializar los Datos del Catálogo**

Antes de ejecutar la aplicación, necesitas insertar los datos en MongoDB.

#### Opción 1: Usando MongoDB Compass (Recomendado)

1. Abre **MongoDB Compass**
2. Conéctate a `mongodb://localhost:27017`
3. En la parte superior, haz clic en el botón **`_MONGOSH`**
4. Copia y pega el contenido del archivo:
   ```
   src/main/resources/scripts/init-catalogo.js
   ```
5. Presiona **Enter** para ejecutar
6. Deberías ver un mensaje de confirmación con el conteo de items insertados

#### Opción 2: Usando línea de comandos (si tienes mongosh)

```bash
# Desde la raíz del proyecto
mongosh < src/main/resources/scripts/init-catalogo.js
```

---

### **PASO 2: Ejecutar la Aplicación**

```bash
# Desde la raíz del proyecto
./mvnw spring-boot:run
```

Espera a ver este mensaje:

```
Started MsCatalogoTesisApplication in X.XXX seconds
```

La aplicación estará corriendo en: **http://localhost:8083**

---

### **PASO 3: Probar con Swagger UI** ⭐ **(MÁS FÁCIL)**

1. Abre tu navegador
2. Ve a: **http://localhost:8083/swagger-ui.html**
3. Verás la interfaz interactiva de Swagger con todos los endpoints

#### Probar el endpoint "GET /api/catalogo"

1. Haz clic en **`GET /api/catalogo`** para expandirlo
2. Haz clic en **"Try it out"**
3. Haz clic en **"Execute"**
4. Verás la respuesta con todos los elementos del catálogo

#### Probar el endpoint "GET /api/catalogo/grupo/{grupo}"

1. Haz clic en **`GET /api/catalogo/grupo/{grupo}`**
2. Haz clic en **"Try it out"**
3. En el campo `grupo`, escribe: **ESTADO_PROYECTO**
4. Haz clic en **"Execute"**
5. Verás todos los estados de proyecto

#### Probar endpoint "GET por grupo y código"

1. Haz clic en **`GET /api/catalogo/grupo/{grupo}/codigo/{codigo}`**
2. Haz clic en **"Try it out"**
3. En `grupo`: **ESPECIALIDAD**
4. En `codigo`: **INGENIERIA_SOFTWARE**
5. Haz clic en **"Execute"**
6. Verás el detalle de esa especialidad

---

### **PASO 4: Probar con cURL** (Alternativo)

Abre una nueva terminal (PowerShell) y ejecuta:

#### Listar todos los catálogos

```bash
curl http://localhost:8083/api/catalogo
```

#### Obtener estados de proyecto

```bash
curl http://localhost:8083/api/catalogo/grupo/ESTADO_PROYECTO
```

#### Obtener solo elementos activos

```bash
curl http://localhost:8083/api/catalogo/grupo/ROL_JURADO/activos
```

#### Obtener un elemento específico

```bash
curl http://localhost:8083/api/catalogo/grupo/ESPECIALIDAD/codigo/INGENIERIA_SOFTWARE
```

#### Crear un nuevo elemento

```bash
curl -X POST http://localhost:8083/api/catalogo `
  -H "Content-Type: application/json" `
  -d '{
    "grupo": "TEST",
    "codigo": "VALOR1",
    "nombre": "Valor de Prueba",
    "activo": true,
    "orden": 1
  }'
```

---

### **PASO 5: Verificar los Logs**

Los logs se guardan automáticamente en:

```
logs/ms_catalogo_tesis.log
```

Para ver los logs en tiempo real:

```bash
# En Windows PowerShell
Get-Content logs/ms_catalogo_tesis.log -Wait -Tail 50
```

---

## 🔍 Verificación en MongoDB Compass

Después de probar, puedes verificar los datos directamente en MongoDB:

1. Abre **MongoDB Compass**
2. Conecta a `mongodb://localhost:27017`
3. Selecciona la base de datos: **`ms_catalogo_tesis`**
4. Selecciona la colección: **`catalogo`**
5. Verás todos los documentos insertados

---

## 📊 Pruebas Recomendadas

### ✅ **Prueba 1: Listar todos los catálogos**

- **Endpoint:** `GET /api/catalogo`
- **Resultado esperado:** Lista de todos los elementos agrupados
- **Total de items:** ~32 elementos

### ✅ **Prueba 2: Filtrar por grupo**

- **Endpoint:** `GET /api/catalogo/grupo/ESTADO_PROYECTO`
- **Resultado esperado:** 6 estados de proyecto

### ✅ **Prueba 3: Solo elementos activos**

- **Endpoint:** `GET /api/catalogo/grupo/ROL_JURADO/activos`
- **Resultado esperado:** 3 roles de jurado activos

### ✅ **Prueba 4: Buscar específico**

- **Endpoint:** `GET /api/catalogo/grupo/ESPECIALIDAD/codigo/CIENCIAS_COMPUTACION`
- **Resultado esperado:** Objeto con nombre "CIENCIAS DE LA COMPUTACION"

### ✅ **Prueba 5: Crear nuevo elemento**

- **Endpoint:** `POST /api/catalogo`
- **Body:**
  ```json
  {
    "grupo": "TIPO_DOCUMENTO",
    "codigo": "DNI",
    "nombre": "Documento Nacional de Identidad",
    "activo": true,
    "orden": 1
  }
  ```
- **Resultado esperado:** Código 201 Created

### ✅ **Prueba 6: Actualizar elemento**

- **Endpoint:** `PUT /api/catalogo/{id}`
- **Nota:** Primero obtén un ID válido con GET /api/catalogo
- **Body:**
  ```json
  {
    "grupo": "TIPO_DOCUMENTO",
    "codigo": "DNI",
    "nombre": "DNI - Actualizado",
    "activo": true,
    "orden": 1
  }
  ```

### ✅ **Prueba 7: Eliminar elemento**

- **Endpoint:** `DELETE /api/catalogo/{id}`
- **Resultado esperado:** Código 204 No Content

---

## 🐛 Solución de Problemas

### ❌ Error: "Connection refused to MongoDB"

**Solución:** MongoDB no está corriendo. Inicia el servicio MongoDB.

### ❌ Error: "Collection not found"

**Solución:** No ejecutaste el script de inicialización. Ve al PASO 1.

### ❌ Error: "Port 8083 already in use"

**Solución:** Otro servicio está usando el puerto. Cambia el puerto en `application.properties`:

```properties
server.port=8084
```

### ❌ Error de compilación

**Solución:** Ejecuta:

```bash
./mvnw clean install
```

---

## 📱 Interfaces de Prueba Disponibles

1. **Swagger UI**: http://localhost:8083/swagger-ui.html ⭐ **(Recomendado)**
2. **API Docs JSON**: http://localhost:8083/api-docs
3. **Endpoints REST**: http://localhost:8083/api/catalogo
4. **MongoDB Compass**: Para ver los datos directamente

---

## 🎯 Resultado Esperado

Si todo funciona correctamente:

✅ La aplicación inicia en el puerto 8083  
✅ Swagger UI muestra todos los endpoints  
✅ GET /api/catalogo retorna ~32 elementos  
✅ MongoDB tiene la colección `catalogo` con datos  
✅ Los logs se escriben en `logs/ms_catalogo_tesis.log`  
✅ Todas las operaciones CRUD funcionan correctamente

---

## 🎉 ¡Listo para Usar!

El microservicio está completamente funcional y listo para integrarse con otros servicios.
