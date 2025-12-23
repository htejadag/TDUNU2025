package com.service.MsTramiteTesis.util;

public class ApiRoutes {

    public static class TramiteTesis {

        public static final String BASE = "/api/tramites";

        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId/{id}";
        public static final String GUARDAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }
}
