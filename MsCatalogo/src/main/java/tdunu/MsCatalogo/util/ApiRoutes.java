package tdunu.MsCatalogo.util;

public class ApiRoutes {

    public static class Catalogo {
        public static final String BASE = "/api/catalogo";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String ELIMINAR = "/eliminar";
        public static final String BUSCAR_POR_TIPO = "/buscarPorTipo";
        public static final String BUSCAR_POR_CODIGO = "/buscarPorCodigo";
        public static final String LIMPIAR_CACHE = "/limpiarCache";
    }

    public static class TipoCatalogo {
        public static final String BASE = "/api/tipo-catalogo";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String ELIMINAR = "/eliminar";
    }
}
