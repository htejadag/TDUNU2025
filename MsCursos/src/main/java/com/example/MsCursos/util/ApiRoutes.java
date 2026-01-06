package com.example.MsCursos.util;

public class ApiRoutes {
    public static class Curso {
        public static final String BASE = "/api/curso";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ELIMINAR = "/eliminar";

        public static final String BUSCAR = "/buscar"; // ?carrera=1&estado=true
    }

    public static class CursoDetalle {
        public static final String BASE = "/api/curso-detalle";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ELIMINAR = "/eliminar";
    }

    public static class Catalogo {
        public static final String BASE = "/api/catalogo";

        public static final String LISTAR = "/listar";
        public static final String OBTENER = "/{id}";

        public static final String POR_CATEGORIA = "/categoria/{categoria}";
        public static final String POR_ESTADO = "/estado/{estado}";
        public static final String POR_CATEGORIA_ESTADO = "/categoria/{categoria}/estado/{estado}";
        public static final String POR_PADRE = "/padre/{idPadre}";

        public static final String GUARDAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String CAMBIAR_ESTADO = "/cambiar-estado/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }
}
