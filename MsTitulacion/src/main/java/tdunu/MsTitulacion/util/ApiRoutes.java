package tdunu.MsTitulacion.util;

public class ApiRoutes {
    
    public static final String API_PATH_GENERAL = "/api/titulacion";

    public static class Dictamen{
        public static final String BASE = API_PATH_GENERAL + "/dictamen";
        public static final String LISTAR = "/listar";
        public static final String LISTARCATRESULTADO = "/listarResultado/{categoria}";
        public static final String REGISTRAR = "/guardar";
        public static final String OBTENER = "/obtenerPorId/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }

    public static class ResolucionTitulo{
        public static final String BASE = API_PATH_GENERAL + "/resolucion";
        public static final String LISTAR = "/listar";
        public static final String REGISTRAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }

    public static class RevisionBorrador{
        public static final String BASE = API_PATH_GENERAL + "/revision";
        public static final String LISTAR = "/listar";
        public static final String REGISTRAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }

    public static class TesisBorrador{
        public static final String BASE = API_PATH_GENERAL + "/borrador";
        public static final String LISTAR = "/listar";
        public static final String REGISTRAR = "/guardar";
        public static final String OBTENER = "/obtenerPorId/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }

}
