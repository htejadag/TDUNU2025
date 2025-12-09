package com.example.MsGeneral.util;

public class ApiRoutes {

    public static final String BASEGENERAL = "/ms-general";
    
    public static class UnidadOrganica {
        public static final String BASE = "/unidadOrganica";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId/{id}";
        public static final String CREAR = "/crear";
        public static final String ELIMINAR = "/eliminar/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
    }

}
