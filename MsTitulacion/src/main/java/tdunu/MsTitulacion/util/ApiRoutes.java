package tdunu.MsTitulacion.util;

public class ApiRoutes {

    // Prefijo global de la API
    public static final String API_ROOT = "/api/titulacion";

    public static class Dictamen {
        public static final String BASE = API_ROOT + "/dictamen";
        public static final String REGISTRAR = "/registrar";
    }

    public static class Acta {
        public static final String BASE = API_ROOT + "/acta";
        public static final String POR_PROYECTO = "/proyecto/{idProyecto}";
    }

    public static class Catalogo {
        public static final String BASE = API_ROOT + "/catalogo";
        public static final String LISTAR_POR_CATEGORIA = "/categoria/{categoria}";
    }
}