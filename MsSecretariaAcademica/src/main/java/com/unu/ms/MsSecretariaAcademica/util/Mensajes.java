package com.unu.ms.MsSecretariaAcademica.util;

public class Mensajes {

    // Genéricos OK
    public static final String LISTAR_OK = "Se cargó y listó correctamente.";
    public static final String CREADO_OK = "Se creó un nuevo registro correctamente.";
    public static final String ACTUALIZADO_OK = "Se actualizó correctamente.";
    public static final String ELIMINADO_OK = "Se eliminó correctamente.";
    public static final String OBTENER_POR_OK = "Se encontró el registro exitosamente.";

    // Errores genéricos
    public static final String ERROR_DESCONOCIDO = "Ocurrió un error inesperado.";
    public static final String NO_ENCONTRADO = "No se encontró el recurso solicitado.";
    public static final String CAMPOS_OBLIGATORIOS = "Faltan campos obligatorios.";
    public static final String LISTA_VACIA = "No se encontraron datos.";

    // Errores específicos centralizados
    public static final String ENTIDAD_NO_ENCONTRADA = "La entidad solicitada no existe o no pudo ser encontrada.";
    public static final String VIOLACION_RESTRICCION = "Se violó una restricción de la base de datos (duplicado, clave foránea, etc.).";
    public static final String ERROR_PERSISTENCIA = "Error al acceder a la base de datos.";
    public static final String JSON_INVALIDO = "El cuerpo de la solicitud contiene un JSON inválido o mal formado.";
    public static final String ERROR_CONEXION_BD = "No se pudo conectar a la base de datos. Intente nuevamente más tarde.";
    public static final String ERROR_ACCESO_DATOS = "Ocurrió un error al acceder a los datos.";
    public static final String FORMATO_NUMERICO_INVALIDO = "Formato numérico inválido. Verifique los valores enviados.";
    
}
