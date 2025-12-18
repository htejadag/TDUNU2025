package com.unu.ms.MsConsejo.util;

public class ApiRoutes {

    public static class Consejo {
        public static final String BASE = "/api/consejo";
        public static final String LISTAR = "/listar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String GUARDAR = "/guardar";
        public static final String ELIMINAR = "/eliminar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";

        public static final String BUSCAR_POR_NOMBRE = "/buscarPorNombre";
        public static final String BUSCAR_POR_ESTADO = "/buscarPorEstado";
    }

    public static class AsistenciaSesion {
        public static final String BASE = "/api/asistenciaSesion";
        public static final String LISTAR = "/listar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String GUARDAR = "/guardar";
        public static final String ELIMINAR = "/eliminar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";

        public static final String BUSCAR_POR_SESION = "/buscarPorSesion";
        public static final String BUSCAR_POR_MIEMBRO = "/buscarPorMiembro";
        public static final String BUSCAR_POR_ESTADO = "/buscarPorEstado";
    }

    public static class MiembroConsejo {
        public static final String BASE = "/api/miembroConsejo";
        public static final String LISTAR = "/listar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String GUARDAR = "/guardar";
        public static final String ELIMINAR = "/eliminar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";

        public static final String BUSCAR_POR_CONSEJO = "/buscarPorConsejo";
        public static final String BUSCAR_POR_PERSONA = "/buscarPorPersona";
        public static final String BUSCAR_POR_CARGO = "/buscarPorCargo";
    }

    public static class SesionConsejo {
        public static final String BASE = "/api/sesionConsejo";
        public static final String LISTAR = "/listar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String GUARDAR = "/guardar";
        public static final String ELIMINAR = "/eliminar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";

        public static final String BUSCAR_POR_CONSEJO = "/buscarPorConsejo";
        public static final String BUSCAR_POR_NUMERO = "/buscarPorNumero";
        public static final String BUSCAR_POR_FECHA = "/buscarPorFecha";
        public static final String BUSCAR_POR_TIPO = "/buscarPorTipo";
    }

    public static class Catalogo {
        public static final String BASE = "/api/catalogo";
        public static final String LISTAR = "/listar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String GUARDAR = "/guardar";
        public static final String ELIMINAR = "/eliminar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";

        public static final String BUSCAR_POR_DESCRIPCION = "/buscarPorDescripcion";
        public static final String BUSCAR_POR_ABREVIATURA = "/buscarPorAbreviatura";
        public static final String BUSCAR_POR_ESTADO = "/buscarPorEstado";
        public static final String BUSCAR_HIJOS_POR_PADRE = "/buscarHijosPorPadre";
        public static final String BUSCAR_CATALOGOS_RAIZ = "/buscarCatalogosRaiz";
        public static final String BUSCAR_POR_PADRE_Y_ESTADO = "/buscarPorPadreYEstado";
    }

}