package com.unu.MsDocumentos.utils;

public class ApiRoutes {

    public static class DocumentoRoutes {
        private DocumentoRoutes() {
            throw new UnsupportedOperationException();
        }

        public static final String BASE = "/api/Documentos";
        public static final String add = "/Crear";
        public static final String listar = "/Listar";
        public static final String listarId = "/ListarId/{id}";
        public static final String update = "/Modificar/{id}";
        public static final String delete = "/Eliminar/{id}";
    }
}
