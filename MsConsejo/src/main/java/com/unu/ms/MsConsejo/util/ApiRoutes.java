package com.unu.ms.MsConsejo.util;

public class ApiRoutes {

    public static class Consejo {
        public static final String BASE = "/api/consejo";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String ELIMINAR = "/eliminar";
        public static final String BUSCAR_POR_NOMBRE = "/buscarPorNombre";
        public static final String BUSCAR_POR_ESTADO = "/buscarPorEstado";
    }

    public static class MiembroConsejo {
        public static final String BASE = "/api/miembro-consejo";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String ELIMINAR = "/eliminar";
        public static final String BUSCAR_POR_CONSEJO = "/buscarPorConsejo";
        public static final String BUSCAR_POR_PERSONA = "/buscarPorPersona";
        public static final String BUSCAR_POR_CARGO = "/buscarPorCargo";
    }

    public static class SesionConsejo {
        public static final String BASE = "/api/sesion-consejo";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String ELIMINAR = "/eliminar";
        public static final String BUSCAR_POR_CONSEJO = "/buscarPorConsejo";
        public static final String BUSCAR_POR_NUMERO = "/buscarPorNumero";
        public static final String BUSCAR_POR_FECHA = "/buscarPorFecha";
        public static final String BUSCAR_POR_TIPO = "/buscarPorTipo";
    }

    public static class AsistenciaConsejero {
        public static final String BASE = "/api/asistencia-consejero";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String ELIMINAR = "/eliminar";
        public static final String BUSCAR_POR_SESION = "/buscarPorSesion";
        public static final String BUSCAR_POR_MIEMBRO = "/buscarPorMiembro";
        public static final String BUSCAR_POR_ESTADO = "/buscarPorEstado";
    }
}
