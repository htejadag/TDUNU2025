# ¿Cómo funciona la integración con MsCatalogo?

A diferencia de `MsAuditoria` (donde usamos Kafka), la interacción con un **Catálogo** (MsCatalogo) suele ser muy diferente. Aquí te explico por qué y cómo funciona el flujo.

## Diferencia Clave: "¿Lo necesito ahora?"

- **Auditoría (Kafka) -> "Avísales y sigue"**:

  - No necesitas la respuesta de Auditoría para guardar el proyecto.
  - Si Auditoría tarda 1 minuto en guardar, a ti no te importa.
  - Por eso usamos **Kafka (Asíncrono)**.

- **Catálogo (REST/Feign) -> "Pregunta y espera"**:
  - Imagina que el alumno va a elegir su "Especialidad".
  - Tu sistema (`MsTramiteTesis`) NO tiene esa lista. La tiene `MsCatalogo`.
  - Necesitas esa lista **YA MISMO** para mostrársela al alumno en la pantalla.
  - Por eso usamos **REST API (Síncrono/Inmediato)**.

---

## Flujo Paso a Paso: Consultar un Catálogo

Digamos que el Alumno entra al formulario para crear su tesis.

### Paso 1: El Alumno entra a la Pantalla

El navegador le dice a tu sistema: _"Oye, dame la lista de Especialidades para llenar el combo (dropdown)"_.

### Paso 2: Tu Sistema no sabe

Tu código (`MsTramiteTesis`) dice: _"Yo solo manejo Proyectos, no sé qué especialidades existen actualmente. Tengo que preguntarle al experto (MsCatalogo)"_.

### Paso 3: La Llamada Telefónica (REST API)

Tu sistema hace una petición directa a `MsCatalogo` (usando `RestTemplate` o `FeignClient`).

- **Tú**: _"Hola MsCatalogo, dame la lista de especialidades activas"_.
- **Tu Sistema se congela (espera)** unos milisegundos mientras espera la respuesta.

### Paso 4: La Respuesta

- **MsCatalogo**: _"Claro, aquí tienes: [Ingeniería de Sistemas, Ingeniera Civil, Agronomía]"_.
- Tu sistema recibe la lista.

### Paso 5: Mostrar al Usuario

Tu sistema toma esa lista y se la envía al navegador del alumno.

- **Resultado**: El alumno ve las opciones en el menú desplegable.

---

## Resumen Comparativo

| Característica            | MsAuditoria (Kafka)                                 | MsCatalogo (REST API)                                 |
| :------------------------ | :-------------------------------------------------- | :---------------------------------------------------- |
| **Tipo de Comunicación**  | **Asíncrona** (Carta al buzón)                      | **Síncrona** (Llamada telefónica)                     |
| **¿Esperamos respuesta?** | NO. Enviamos y seguimos trabajando.                 | SÍ. Esperamos el dato para poder continuar.           |
| **Si el otro falla...**   | No pasa nada, Kafka guarda el mensaje para después. | **Error**. No podemos mostrar la lista al alumno.     |
| **Uso ideal**             | Guardar historiales, notificaciones, emails.        | Consultar datos maestros (Listas, Precios, Usuarios). |

## ¿Cómo se implementa esto en código?

Para `MsCatalogo` no usaríamos Producer/Consumer. Usaríamos:

1.  **FeignClient**: Una interfaz en Java que "finge" ser el otro microservicio.
2.  **DTO**: Una clase Java para recibir los datos (ej: `EspecialidadDTO`).
