package com.example.MsCuenta.Util;

public class ApiRoutes {

     private ApiRoutes() {}

    public static class CuentaUsuario{
    
        private CuentaUsuario() {}
    

        public static final String BASE = "/api/cuenta_usuario";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String MODIFICAR = "/modificar";
        public static final String ELIMINAR = "/eliminar";  
    }

    public static class Recarga{

        private Recarga(){}
        
        public static final String BASE_RECARGA = "/api/recarga";
        public static final String LISTAR_RECARGA = "/listar";
        public static final String OBTENER_POR_ID_RECARGA = "/obtenerPorId";
        public static final String GUARDAR_RECARGA = "/guardar";
        public static final String MODIFICAR_RECARGA = "/modificar";
        public static final String ELIMINAR_RECARGA = "/eliminar";
    }

    public static class Movimiento{

        private Movimiento() {}
        
        public static final String BASE_MOVIMIENTO = "/api/movimiento";
        public static final String LISTAR_MOVIMIENTO = "/listar";
        public static final String OBTENER_POR_ID_MOVIMIENTO = "/obtenerPorId";
        public static final String LISTAR_MOVIMIENTO_POR_USUARIO = "/listarPorUsuario";
        public static final String GUARDAR_MOVIMIENTO = "/guardar";
        public static final String MODIFICAR_MOVIMIENTO = "/modificar";
        public static final String ELIMINAR_MOVIMIENTO = "/eliminar";
    }


    public static class Catalogo{

        private Catalogo() {}
        
        public static final String BASE_CATALOGO = "/api/catalogo";
        public static final String LISTAR_CATALOGO= "/listar";
        public static final String OBTENER_POR_ID_CATALOGO = "/obtenerPorId";
        public static final String GUARDAR_CATALOGO = "/guardar";
        public static final String MODIFICAR_CATALOGO = "/modificar";
        public static final String ELIMINAR_CATALOGO = "/eliminar";
        
    }

}
