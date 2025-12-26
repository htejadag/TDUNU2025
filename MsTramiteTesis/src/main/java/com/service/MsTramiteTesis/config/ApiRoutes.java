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
    }

    public static class ProyectosEnriquecidos {
        public static final String BASE = API_BASE + "/proyectos-enriquecidos";
        public static final String BY_ID = "/{id}";
    }

    public static class Testing {
        public static final String BASE_KAFKA = Proyectos.BASE + "/test/kafka";
        public static final String BASE_ALL = Proyectos.BASE + "/test/all";
        public static final String CREAR_PROYECTO = "";
        public static final String ACTUALIZAR_ESTADO = "/{id}/estado";
        public static final String ELIMINAR_PROYECTO = "/{id}";
    }

    private ApiRoutes() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no debe ser instanciada");
    }
}
