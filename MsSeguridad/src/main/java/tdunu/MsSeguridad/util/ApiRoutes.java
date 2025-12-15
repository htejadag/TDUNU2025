package tdunu.MsSeguridad.util;

public class ApiRoutes {

    public static class SISTEMA {

        public static final String BASE = "/sistemas";
        public static final String LISTAR = "/listar";
        public static final String GUARDAR = "/guardar";
        public static final String ELIMINAR = "/eliminar";
        public static final String OBTENER_POR_ID = "/obtener";
    }

    public static class USUARIO {

        public static final String BASE = "/usuarios";
        public static final String LISTAR = "/listar";
        public static final String LISTAR_ACTIVOS = "/activos";
        public static final String LISTAR_DESACTIVADOS = "/inactivos";
        public static final String GUARDAR = "/guardar";
        public static final String EDITAR = "/editar";
        public static final String ELIMINAR = "/eliminar";
        public static final String OBTENER_POR_CODIGO = "/buscar";
        public static final String ROLES = "/roles";
    }

    public static class ROLE {

        public static final String BASE = "/roles";
        public static final String LISTAR = "/listar";
        public static final String GUARDAR = "/guardar";
        public static final String EDITAR = "/editar";
        public static final String ELIMINAR = "/eliminar";
        public static final String PERMISOS = "/permisos";
    }

    public static class PERMISO {

        public static final String BASE = "/permisos";
        public static final String LISTAR = "/listar";
        public static final String GUARDAR = "/guardar";
        public static final String EDITAR = "/editar";
        public static final String ELIMINAR = "/eliminar";
    }
}
