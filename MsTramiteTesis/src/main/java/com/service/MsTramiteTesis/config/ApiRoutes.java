package com.service.MsTramiteTesis.config;

/**
 * Centralización de todas las rutas de la API.
 * Mejora la mantenibilidad y previene errores de typos en las rutas.
 */
public class ApiRoutes {

    private static final String API_BASE = "/api";

    /**
     * Rutas para los endpoints de Estudiante
     */
    public static class Estudiante {
        private static final String BASE = API_BASE + "/estudiante";

        /**
         * Rutas para gestión de proyectos por estudiantes
         */
        public static class Proyectos {
            public static final String BASE = Estudiante.BASE + "/proyectos";
            public static final String CREAR = "";
            public static final String ACTUALIZAR_PDF = "/{id}/pdf";
            public static final String MIS_PROYECTOS = "";
            public static final String BY_ID = "/{id}";
        }
    }

    /**
     * Rutas para los endpoints de Docente (Asesor y Jurado)
     */
    public static class Docente {
        private static final String BASE = API_BASE + "/docente";

        /**
         * Rutas para revisión de proyectos por docentes
         */
        public static class Proyectos {
            public static final String BASE = Docente.BASE + "/proyectos";
            public static final String ASESORIAS = "/asesorias";
            public static final String REVISION_ASESOR = "/{id}/revision-asesor";
            public static final String JURADO = "/jurado";
            public static final String REVISION_JURADO = "/{id}/revision-jurado";
            public static final String REVISIONES = "/{id}/revisiones";
        }
    }

    /**
     * Rutas para los endpoints de Coordinador
     */
    public static class Coordinador {
        private static final String BASE = API_BASE + "/coordinador";

        /**
         * Rutas para administración de proyectos por coordinador
         */
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

    /**
     * Constructor privado para prevenir instanciación
     */
    private ApiRoutes() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no debe ser instanciada");
    }
}
