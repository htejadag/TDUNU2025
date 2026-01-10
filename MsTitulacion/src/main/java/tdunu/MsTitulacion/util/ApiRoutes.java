package tdunu.MsTitulacion.util;

public class ApiRoutes {
    
    public static final String API_PATH = "/api/titulacion";

    public static class Dictamen{
        public static final String BASE = API_PATH + "/dictamen";
        public static final String REGISTRAR = "/registrar";
    }

    public static class ResolucionTitulo{
        public static final String BASE = API_PATH + "/resolucion";
        public static final String RESOLUCION = "/proyecto/{id_resolucion}"; 
    }

    public static class RevisionBorrador{
        public static final String BASE = API_PATH + "/revision";
    }

    public static class TesisBorrador{
        public static final String BASE = API_PATH + "/borrador";
    }

}
