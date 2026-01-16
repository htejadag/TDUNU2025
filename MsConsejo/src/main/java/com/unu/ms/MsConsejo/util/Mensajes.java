package com.unu.ms.MsConsejo.util;

/**
 * Clase de constantes con mensajes estándar para las respuestas de la API.
 * 
 * Centraliza todos los mensajes de éxito y error que se envían en las respuestas
 * de los endpoints. Esto permite:
 * - Mantener consistencia en los mensajes
 * - Facilitar cambios globales de mensajes
 * - Evitar duplicación de strings
 * - Mejorar la experiencia del cliente con mensajes claros y predecibles
 * 
 * Los mensajes están organizados en dos grupos:
 * 1. Mensajes de éxito (operaciones completadas correctamente)
 * 2. Mensajes de error (situaciones problemáticas o excepcionales)
 * 
 * Uso: En los controladores y servicios, se usan estas constantes en los métodos
 * ResponseBase.ok() y ResponseBase.error() para mantener uniformidad.
 * 
 * Ejemplo:
 * {@code return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);}
 * {@code return ResponseBase.error(Mensajes.ENTIDAD_NO_ENCONTRADA);}
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
public class Mensajes {

    // =========================
    // Mensajes de Éxito Genéricos
    // =========================

    /** Mensaje de éxito para operaciones de listado */
    public static final String LISTAR_OK = "Se cargó y listó correctamente.";
    
    /** Mensaje de éxito para operaciones de creación */
    public static final String CREADO_OK = "Se creó un nuevo registro correctamente.";
    
    /** Mensaje de éxito para operaciones de actualización */
    public static final String ACTUALIZADO_OK = "Se actualizó correctamente.";
    
    /** Mensaje de éxito para operaciones de eliminación */
    public static final String ELIMINADO_OK = "Se eliminó correctamente.";
    
    /** Mensaje de éxito para operaciones de búsqueda */
    public static final String OBTENER_POR_OK = "Se encontró el registro exitosamente.";

    // =========================
    // Mensajes de Error Genéricos
    // =========================

    /** Mensaje para errores inesperados o no categorizados */
    public static final String ERROR_DESCONOCIDO = "Ocurrió un error inesperado.";
    
    /** Mensaje cuando un recurso solicitado no existe */
    public static final String NO_ENCONTRADO = "No se encontró el recurso solicitado.";
    
    /** Mensaje cuando faltan parámetros obligatorios en la solicitud */
    public static final String CAMPOS_OBLIGATORIOS = "Faltan campos obligatorios.";
    
    /** Mensaje cuando una búsqueda no retorna resultados */
    public static final String LISTA_VACIA = "No se encontraron datos.";

    // =========================
    // Mensajes de Error Específicos
    // =========================

    /** Mensaje cuando la entidad solicitada no existe en la base de datos */
    public static final String ENTIDAD_NO_ENCONTRADA = "La entidad solicitada no existe o no pudo ser encontrada.";
    
    /** Mensaje cuando se viola una restricción de integridad (duplicado, clave foránea, etc.) */
    public static final String VIOLACION_RESTRICCION = "Se violó una restricción de la base de datos (duplicado, clave foránea, etc.).";
    
    /** Mensaje genérico para errores de persistencia */
    public static final String ERROR_PERSISTENCIA = "Error al acceder a la base de datos.";
    
    /** Mensaje cuando el JSON de la solicitud es inválido o mal formado */
    public static final String JSON_INVALIDO = "El cuerpo de la solicitud contiene un JSON inválido o mal formado.";
    
    /** Mensaje cuando no se puede conectar a la base de datos */
    public static final String ERROR_CONEXION_BD = "No se pudo conectar a la base de datos. Intente nuevamente más tarde.";
    
    /** Mensaje genérico para errores al acceder a datos */
    public static final String ERROR_ACCESO_DATOS = "Ocurrió un error al acceder a los datos.";
    
    /** Mensaje cuando el formato de un número no es válido */
    public static final String FORMATO_NUMERICO_INVALIDO = "Formato numérico inválido. Verifique los valores enviados.";
}
