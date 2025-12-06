package com.unu.ms.MsGradosTitulos.util;

public class ApuRoutes {
    
    // Rutas base
    public static final String API_V1 = "/api/v1";
    
    // Rutas para Expediente
    public static final String EXPEDIENTE_BASE = API_V1 + "/expedientes";
    public static final String EXPEDIENTE_GET_ALL = EXPEDIENTE_BASE;
    public static final String EXPEDIENTE_GET_BY_ID = EXPEDIENTE_BASE + "/{id}";
    public static final String EXPEDIENTE_CREATE = EXPEDIENTE_BASE;
    public static final String EXPEDIENTE_UPDATE = EXPEDIENTE_BASE + "/{id}";
    public static final String EXPEDIENTE_DELETE = EXPEDIENTE_BASE + "/{id}";
    
}
