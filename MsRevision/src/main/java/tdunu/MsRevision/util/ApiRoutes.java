package tdunu.MsRevision.util;

public class ApiRoutes {

    public static final String API_ROOT = "/api/titulacion";

    // ... (Rutas anteriores Dictamen, Acta, Catalogo)

    public static class Revision {
        public static final String BASE = API_ROOT + "/revision";
        public static final String REGISTRAR = "/registrar";
        public static final String POR_PROYECTO = "/proyecto/{idProyecto}";
        public static final String POR_REVISOR = "/revisor/{idRevisor}";
    }
}