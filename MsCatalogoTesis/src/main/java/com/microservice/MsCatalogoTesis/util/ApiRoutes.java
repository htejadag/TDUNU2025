package com.microservice.MsCatalogoTesis.util;

/**
 * Constantes de rutas de la API
 */
public class ApiRoutes {

    public static class Catalogo {

        public static final String BASE = "/api/catalogo";

        public static final String LISTAR = "";
        public static final String OBTENER_POR_GRUPO = "/grupo/{grupo}";
        public static final String OBTENER_ACTIVOS_POR_GRUPO = "/grupo/{grupo}/activos";
        public static final String OBTENER_POR_GRUPO_Y_CODIGO = "/grupo/{grupo}/codigo/{codigo}";
        public static final String GUARDAR = "";
        public static final String ACTUALIZAR = "/{id}";
        public static final String ELIMINAR = "/{id}";
    }
}
