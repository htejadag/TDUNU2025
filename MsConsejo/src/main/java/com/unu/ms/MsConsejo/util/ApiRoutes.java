package com.unu.ms.MsConsejo.util;

/**
 * Clase de configuración centralizada de rutas de API.
 * 
 * Define todas las rutas (endpoints) disponibles en el microservicio de una manera
 * centralizada para facilitar el mantenimiento y evitar inconsistencias en los
 * nombres de los endpoints.
 * 
 * Estructura: Agrupa todas las rutas por entidad (Consejo, Sesión, Miembro, etc.)
 * con clases internas estáticas que contienen las constantes de ruta.
 * 
 * Uso: En los controladores, se usan estas constantes en las anotaciones de mapeamiento
 * de rutas (@GetMapping, @PostMapping, etc.) para mantener consistencia.
 * 
 * Ejemplo:
 * {@code @GetMapping(ApiRoutes.Consejo.LISTAR)}
 * {@code @PostMapping(ApiRoutes.Catalogo.GUARDAR)}
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
public class ApiRoutes {

    /**
     * Rutas para operaciones relacionadas con consejos.
     * Base: /api/consejo
     */
    public static class Consejo {
        /** Ruta base para operaciones de consejo */
        public static final String BASE = "/api/consejo";
        /** Ruta para listar todos los consejos */
        public static final String LISTAR = "/listar";
        /** Ruta para actualizar un consejo */
        public static final String ACTUALIZAR = "/actualizar";
        /** Ruta para guardar un nuevo consejo */
        public static final String GUARDAR = "/guardar";
        /** Ruta para eliminar un consejo */
        public static final String ELIMINAR = "/eliminar";
        /** Ruta para obtener un consejo por identificador */
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        /** Ruta para buscar un consejo por nombre */
        public static final String BUSCAR_POR_NOMBRE = "/buscarPorNombre";
        /** Ruta para buscar consejos por estado */
        public static final String BUSCAR_POR_ESTADO = "/buscarPorEstado";
    }

    /**
     * Rutas para operaciones relacionadas con asistencia a sesiones.
     * Base: /api/asistenciaSesion
     */
    public static class AsistenciaSesion {
        /** Ruta base para operaciones de asistencia a sesión */
        public static final String BASE = "/api/asistenciaSesion";
        /** Ruta para listar todos los registros de asistencia */
        public static final String LISTAR = "/listar";
        /** Ruta para actualizar un registro de asistencia */
        public static final String ACTUALIZAR = "/actualizar";
        /** Ruta para guardar un nuevo registro de asistencia */
        public static final String GUARDAR = "/guardar";
        /** Ruta para eliminar un registro de asistencia */
        public static final String ELIMINAR = "/eliminar";
        /** Ruta para obtener un registro de asistencia por identificador */
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        /** Ruta para buscar registros de asistencia de una sesión específica */
        public static final String BUSCAR_POR_SESION = "/buscarPorSesion";
        /** Ruta para buscar registros de asistencia de un miembro específico */
        public static final String BUSCAR_POR_MIEMBRO = "/buscarPorMiembro";
        /** Ruta para buscar registros de asistencia por estado */
        public static final String BUSCAR_POR_ESTADO = "/buscarPorEstado";
    }

    /**
     * Rutas para operaciones relacionadas con miembros del consejo.
     * Base: /api/miembroConsejo
     */
    public static class MiembroConsejo {
        /** Ruta base para operaciones de miembro del consejo */
        public static final String BASE = "/api/miembroConsejo";
        /** Ruta para listar todos los miembros */
        public static final String LISTAR = "/listar";
        /** Ruta para actualizar un miembro */
        public static final String ACTUALIZAR = "/actualizar";
        /** Ruta para guardar un nuevo miembro */
        public static final String GUARDAR = "/guardar";
        /** Ruta para eliminar un miembro */
        public static final String ELIMINAR = "/eliminar";
        /** Ruta para obtener un miembro por identificador */
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        /** Ruta para buscar miembros de un consejo específico */
        public static final String BUSCAR_POR_CONSEJO = "/buscarPorConsejo";
        /** Ruta para buscar miembros por identificador de persona */
        public static final String BUSCAR_POR_PERSONA = "/buscarPorPersona";
        /** Ruta para buscar miembros por cargo */
        public static final String BUSCAR_POR_CARGO = "/buscarPorCargo";
    }

    /**
     * Rutas para operaciones relacionadas con sesiones de consejo.
     * Base: /api/sesionConsejo
     */
    public static class SesionConsejo {
        /** Ruta base para operaciones de sesión de consejo */
        public static final String BASE = "/api/sesionConsejo";
        /** Ruta para listar todas las sesiones */
        public static final String LISTAR = "/listar";
        /** Ruta para actualizar una sesión */
        public static final String ACTUALIZAR = "/actualizar";
        /** Ruta para guardar una nueva sesión */
        public static final String GUARDAR = "/guardar";
        /** Ruta para eliminar una sesión */
        public static final String ELIMINAR = "/eliminar";
        /** Ruta para obtener una sesión por identificador */
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        /** Ruta para buscar sesiones de un consejo específico */
        public static final String BUSCAR_POR_CONSEJO = "/buscarPorConsejo";
        /** Ruta para buscar una sesión por número de sesión */
        public static final String BUSCAR_POR_NUMERO = "/buscarPorNumero";
        /** Ruta para buscar sesiones por fecha */
        public static final String BUSCAR_POR_FECHA = "/buscarPorFecha";
        /** Ruta para buscar sesiones por tipo */
        public static final String BUSCAR_POR_TIPO = "/buscarPorTipo";
    }
    
    /**
     * Rutas para operaciones de prueba con Kafka.
     * Base: /api/kafkatest
     */
    public static class KafkaTest {
        /** Ruta base para operaciones de prueba de Kafka */
        public static final String BASE = "/api/kafkatest";
        /** Ruta para enviar un mensaje de prueba a Kafka */
        public static final String ENVIAR_MENSAJE = "/enviarMensaje";
    }

    /**
     * Rutas para operaciones relacionadas con catálogos.
     * Base: /api/catalogo
     */
    public static class Catalogo {
        /** Ruta base para operaciones de catálogo */
        public static final String BASE = "/api/catalogo";
        /** Ruta para listar todos los catálogos */
        public static final String LISTAR = "/listar";
        /** Ruta para actualizar un catálogo */
        public static final String ACTUALIZAR = "/actualizar";
        /** Ruta para crear un nuevo catálogo */
        public static final String GUARDAR = "/crear";
        /** Ruta para eliminar un catálogo */
        public static final String ELIMINAR = "/eliminar";
        /** Ruta para obtener un catálogo por identificador */
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        /** Ruta para buscar un catálogo por categoría y valor */
        public static final String BUSCAR_POR_CATEGORIA_Y_VALOR = "/buscarPorCategoriaYValor";
        /** Ruta para buscar catálogos por categoría */
        public static final String BUSCAR_POR_CATEGORIA = "/buscarPorCategoria";
    }

}