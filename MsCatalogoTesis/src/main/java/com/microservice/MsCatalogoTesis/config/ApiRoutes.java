package com.microservice.MsCatalogoTesis.config;

public class ApiRoutes {

    private static final String API_BASE = "/api";

    public static class Catalogo {
        public static final String BASE = API_BASE + "/catalogo";
        public static final String BY_GRUPO = "/grupo/{grupo}";
        public static final String ACTIVOS_BY_GRUPO = "/grupo/{grupo}/activos";
        public static final String BY_GRUPO_AND_CODIGO = "/grupo/{grupo}/codigo/{codigo}";
        public static final String BY_ID = "/{id}";
    }

    private ApiRoutes() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no debe ser instanciada");
    }
}
