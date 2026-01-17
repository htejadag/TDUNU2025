# ¿Cómo funciona la integración con Kafka? (Explicación Paso a Paso)

Imagina que Kafka es un **Buzón de Correos** de alta velocidad compartida entre tu sistema (`MsTramiteTesis`) y el de tu compañero (`MsAuditoria`).

Aquí te explico detalladamente qué pasará cuando implementemos esto:

## El Escenario

1.  **Tú (`MsTramiteTesis`)** eres el **Remitente (Productor)**. Tu trabajo es hacer trámites, pero también avisar cuando algo importante ocurre.
2.  **Tu Compañero (`MsAuditoria`)** es el **Destinatario (Consumidor)**. Su trabajo es escuchar y anotar todo lo que tú haces para llevar un registro (auditoría).
3.  **Kafka** es el **Sistema de Mensajería**.

---

## Paso a Paso: El Flujo de una Acción

Digamos que un Alumno entra a tu sistema y crea un nuevo proyecto de tesis.

### Paso 1: La Acción de Negocio (Tu Sistema)

El alumno hace clic en "Guardar Proyecto". Tu sistema (`ProyectoService`) hace su trabajo normal:

- Valida los datos.
- Guarda el proyecto en TU base de datos (PostgreSQL).
- **Resultado**: El proyecto ya existe en tu sistema.

### Paso 2: Generar la Notificación (El Aviso)

Justo antes de terminar, tu código dice: _"¡Un momento! Tengo que avisar a Auditoría de que esto pasó"_.

- Tu sistema crea un pequeño paquete de datos (un JSON) llamado `AuditoriaDTO`.
- Este paquete dice algo como:
  ```json
  {
    "id": "123",
    "usuario": "Juan Perez",
    "accion": "CREAR_PROYECTO",
    "fecha": "2026-06-01 10:00:00"
  }
  ```

### Paso 3: Enviar al Buzón (Kafka Producer)

Tu sistema toma ese paquetito JSON y se lo entrega a Kafka.

- Le dices a Kafka: _"Pon esto en el buzón llamado `auditoria-events`"_.
- Kafka recibe el mensaje y dice: _"Recibido. Lo guardaré aquí hasta que alguien lo pida"_.
- **Importante**: Tu sistema **NO ESPERA** a que el compañero responda. Tú entregas el mensaje y sigues trabajando. Esto hace que tu sistema sea rápido.

### Paso 4: Recoger el Mensaje (Kafka Consumer - Tu Compañero)

El sistema de tu compañero (`MsAuditoria`) está constantemente vigilando el buzón `auditoria-events`.

- En cuanto Kafka recibe tu mensaje, `MsAuditoria` lo ve.
- Lo toma y lo lee.

### Paso 5: Procesar y Guardar (Tu Compañero)

El código de tu compañero abre el paquete JSON.

- Lee: "Ah, Juan Perez creó el proyecto 123".
- Toma esa información y la guarda en **SU** base de datos de auditoría.

---

## Resumen de Responsabilidades

| Quién                       | Qué hace                  | Responsabilidad                                                                                                      |
| :-------------------------- | :------------------------ | :------------------------------------------------------------------------------------------------------------------- |
| **MsTramiteTesis (Tú)**     | **PRODUCE** el evento     | Solo te preocupas por ENVIAR el aviso ("Ya creé el proyecto, aquí están los datos"). No te importa qué hacen con él. |
| **Kafka**                   | **TRANSPORTA** el mensaje | Asegura que el mensaje no se pierda y llegue al destino.                                                             |
| **MsAuditoria (Compañero)** | **CONSUME** el evento     | Su única tarea es recoger los mensajes y guardarlos en su historial.                                                 |

## ¿Qué necesitamos para que esto ocurra?

1.  **Infraestructura**: Kafka debe estar encendido (por eso el `docker-compose.yml`).
2.  **Código Productor**: Tu sistema necesita la "antena" para conectar con Kafka y la lógica para "armar el paquete" (el DTO).
3.  **Acuerdo**: Ambos deben estar de acuerdo en qué lleva el paquete (los campos del JSON). Si tú envías "nombre" y él espera "username", fallará.
