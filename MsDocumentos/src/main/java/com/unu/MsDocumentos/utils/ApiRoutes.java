package com.unu.MsDocumentos.utils;

public class ApiRoutes {

    public static class DocumentoRoutes {
        private DocumentoRoutes() {
            throw new UnsupportedOperationException();
        }

        public static final String BASE = "/api/Documentos";
        public static final String add = "/Registrar";
    }
}
