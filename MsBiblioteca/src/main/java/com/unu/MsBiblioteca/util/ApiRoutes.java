package com.unu.MsBiblioteca.util;

public class ApiRoutes {
    
    public static class Libro {
        public static final String BASE = "/api/libro";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";
        
        // ¡IMPORTANTE! Las llaves {id} son necesarias para @PathVariable
        public static final String OBTENER_POR_ID = "/obtener/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }
}