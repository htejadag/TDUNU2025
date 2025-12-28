package Ms_Reingresante.Ms_Reingresante.util;

public final class ApiRoutes {

    private ApiRoutes() {
        // Evita instanciación
    }

    public static final class Resolucion {

        private Resolucion() {
            // Evita instanciación
        }

        public static final String BASE = "/api/resolucion";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ELIMINAR = "/eliminar";
    }

    public static final class ProcesoReingresante {

        private ProcesoReingresante() {
            // Evita instanciación
        }

        public static final String BASE = "/api/proReingresante";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ELIMINAR = "/eliminar";
    }

    public static final class SolicitudReingreso {

        private SolicitudReingreso() {
            // Evita instanciación
        }

        public static final String BASE = "/api/solicitudReingreso";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ELIMINAR = "/eliminar";
    }

    public static final class InformeAcademico {

        private InformeAcademico() {
            // Evita instanciación
        }

        public static final String BASE = "/api/InformeAcademico";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ELIMINAR = "/eliminar";
    }

    public static final class FichaNoAdeudo {

        private FichaNoAdeudo() {
            // Evita instanciación
        }

        public static final String BASE = "/api/FichaNoAdeudo";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ELIMINAR = "/eliminar";
    }

    public static final class MatriculaReingresante {

        private MatriculaReingresante() {
            // Evita instanciación
        }

        public static final String BASE = "/api/Matricula";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ELIMINAR = "/eliminar";
    }
}