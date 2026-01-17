package com.service.MsTramiteTesis.config;

public class ApiRoutes {

    private static final String API_BASE = "/api";

    public static class Estudiante {
        private static final String BASE = API_BASE + "/estudiante";

        public static class Proyectos {
            public static final String BASE = Estudiante.BASE + "/proyectos";
            public static final String CREAR = "";
            public static final String ACTUALIZAR_PDF = "/{id}/pdf";
            public static final String MIS_PROYECTOS = "";
            public static final String BY_ID = "/{id}";
        }
    }

    public static class Docente {
        private static final String BASE = API_BASE + "/docente";

        public static class Proyectos {
            public static final String BASE = Docente.BASE + "/proyectos";
            public static final String ASESORIAS = "/asesorias";
            public static final String REVISION_ASESOR = "/{id}/revision-asesor";
            public static final String JURADO = "/jurado";
            public static final String REVISION_JURADO = "/{id}/revision-jurado";
            public static final String REVISIONES = "/{id}/revisiones";
        }
    }

    public static class Coordinador {
        private static final String BASE = API_BASE + "/coordinador";

        public static class Proyectos {
            public static final String BASE = Coordinador.BASE + "/proyectos";
            public static final String PENDIENTES = "/pendientes";
            public static final String TODOS = "";
            public static final String BY_ID = "/{id}";
            public static final String REVISION = "/{id}/revision";
            public static final String ASIGNAR_JURADOS = "/{id}/asignar-jurados";
            public static final String JURADO_BY_ID = "/jurados/{idAsignacion}";
            public static final String JURADOS_DEL_PROYECTO = "/{id}/jurados";
        }
    }

    private ApiRoutes() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no debe ser instanciada");
    }
}
