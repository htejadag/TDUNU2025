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
}
