package tdunu.MsTitulacion.util;

public class ApiRoutes {
    
    public static final String API_PATH_GENERAL = "/api/titulacion";

    public static class Dictamen{
        public static final String BASE = API_PATH_GENERAL + "/dictamen";
        public static final String REGISTRAR = "/registrar";
    }

    public static class ResolucionTitulo{
        public static final String BASE = API_PATH_GENERAL + "/resolucion";
        public static final String RESOLUCION = "/proyecto/{id_resolucion}"; 
    }

    public static class RevisionBorrador{
        public static final String BASE = API_PATH_GENERAL + "/revision";
    }

    public static class TesisBorrador{
        public static final String BASE = API_PATH_GENERAL + "/borrador";
    }

}
