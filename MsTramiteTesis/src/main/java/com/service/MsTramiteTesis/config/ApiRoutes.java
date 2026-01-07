package com.service.MsTramiteTesis.config;

public class ApiRoutes {

    private static final String API_BASE = "/api";

    public static class Proyectos {
        public static final String BASE = API_BASE + "/proyectos";
        public static final String ACTUALIZAR_PDF = "/{id}/actualizar-pdf";
        public static final String MIS_PROYECTOS = "/mis-proyectos";
        public static final String REVISION_COORDINADOR = "/{id}/revision-coordinador";
        public static final String PENDIENTES_COORDINADOR = "/pendientes-coordinador";
        public static final String TODOS = "/todos";
        public static final String REVISION_ASESOR = "/{id}/revision-asesor";
        public static final String MIS_ASESORIAS = "/mis-asesorias";
        public static final String BY_ID = "/{id}";
        public static final String ENRIQUECIDOS = "/enriquecidos";
        public static final String ENRIQUECIDO_BY_ID = "/enriquecidos/{id}";
    }

    private ApiRoutes() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no debe ser instanciada");
    }
}
