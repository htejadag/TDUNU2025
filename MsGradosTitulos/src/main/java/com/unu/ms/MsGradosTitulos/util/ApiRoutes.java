package com.unu.ms.MsGradosTitulos.util;

public class ApiRoutes {

    public static class Expediente {
        public static final String BASE = "/api/expediente";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String ELIMINAR = "/eliminar";
        public static final String BUSCAR_POR_CODIGO = "/buscarPorCodigo";
        public static final String BUSCAR_POR_PERSONA = "/buscarPorPersona";
    }

    public static class Resolucion {
        public static final String BASE = "/api/resolucion";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String ELIMINAR = "/eliminar";
        public static final String BUSCAR_POR_EXPEDIENTE = "/buscarPorExpediente";
        public static final String BUSCAR_POR_NUMERO = "/buscarPorNumero";
    }

    public static class ResolucionArticulo {
        public static final String BASE = "/api/resolucion-articulo";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String ELIMINAR = "/eliminar";
        public static final String BUSCAR_POR_RESOLUCION = "/buscarPorResolucion";
    }

    public static class Seguimiento {
        public static final String BASE = "/api/seguimiento";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String ELIMINAR = "/eliminar";
        public static final String BUSCAR_POR_ENTIDAD = "/buscarPorEntidad";
    }
}
