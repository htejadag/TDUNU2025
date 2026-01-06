package com.example.MsAuditoria.Util;

public class ApiRoutes {

    public static final String BASEGENERAL = "/ms-auditoria";
    
    public static class Auditoria {
        public static final String BASE = "/auditoria";
        public static final String LISTAR = "/listar";
        public static final String LISTAR_POR_ENTIDAD = "/listarPorEntidad/{entidad}/{idEntidad}";
        public static final String LISTAR_POR_MICROSERVICIO = "/listarPorMicroservicio/{microservicio}";
    }
}
