package com.example.Comedor.util;

public class ApiRoutes {

    private ApiRoutes(){}

    public static class MenuSemana{

        private MenuSemana(){}

        public static final String BASE_MENUSEMANA = "/api/menu_semana";
        public static final String LISTAR_MENUSEMANA = "/listar";
        public static final String OBTENER_POR_ID_MENUSEMANA = "/obtenerPorId";
        public static final String GUARDAR_MENUSEMANA = "/guardar";
        public static final String MODIFICAR_MENUSEMANA = "/modificar";
        public static final String ELIMINAR_MENUSEMANA = "/eliminar";

    }

    public static class Plato {

        private Plato(){}

        public static final String BASE_PLATO = "/api/plato";
        public static final String LISTAR_PLATO = "/listar";
        public static final String OBTENER_POR_ID_PLATO = "/obtenerPorId";
        public static final String GUARDAR_PLATO = "/guardar";
        public static final String MODIFICAR_PLATO = "/modificar";
        public static final String ELIMINAR_PLATO = "/eliminar";
        
    }


    public static class Turno{

        private Turno(){}

        public static final String BASE_TURNO = "/api/turno";
        public static final String LISTAR_TURNO = "/listar";
        public static final String OBTENER_POR_ID_TURNO = "/obtenerPorId";
        public static final String GUARDAR_TURNO = "/guardar";
        public static final String MODIFICAR_TURNO = "/modificar";
        public static final String ELIMINAR_TURNO = "/eliminar";



    }


    public static class MenuDia{

        private MenuDia(){}

        public static final String BASE_MENU_DIA = "/api/menu_dia";
        public static final String LISTAR_MENU_DIA = "/listar";
        public static final String OBTENER_POR_ID_MENU_DIA = "/obtenerPorId";
        public static final String GUARDAR_MENU_DIA = "/guardar";
        public static final String MODIFICAR_MENU_DIA = "/modificar";
        public static final String ELIMINAR_MENU_DIA = "/eliminar";
    }



    public static class MenuPlato {


        private MenuPlato(){}

        public static final String BASE_MENU_PLATO = "/api/menu_plato";
        public static final String LISTAR_MENU_PLATO = "/listar";
        public static final String OBTENER_POR_ID_MENU_PLATO = "/obtenerPorId";
        public static final String GUARDAR_MENU_PLATO = "/guardar";
        public static final String MODIFICAR_MENU_PLATO = "/modificar";
        public static final String ELIMINAR_MENU_PLATO = "/eliminar";
    
        
    }


    public static class ConsumoRacion {
        
        private ConsumoRacion(){}

        public static final String BASE_CONSUMO = "/api/consumo_racion";
        public static final String LISTAR_CONSUMO  = "/listar";
        public static final String OBTENER_POR_ID_CONSUMO  = "/obtenerPorId";
        public static final String GUARDAR_CONSUMO  = "/guardar";
        public static final String MODIFICAR_CONSUMO  = "/modificar";
        public static final String ELIMINAR_CONSUMO  = "/eliminar";

    
        
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
    

