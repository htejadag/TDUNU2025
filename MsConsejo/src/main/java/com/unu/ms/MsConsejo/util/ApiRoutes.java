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
        
        // Nuevos endpoints
        public static final String VIGENTES = "/vigentes";
        public static final String DETALLE_COMPLETO = "/{id}/detalle-completo";
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
        
        // Nuevo endpoint
        public static final String RESUMEN_POR_SESION = "/sesion/{id}/resumen";
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
        
        // Nuevo endpoint - miembros activos por cargo
        public static final String POR_CARGO = "/por-cargo/{idCargo}";
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
        
        // Nuevos endpoints
        public static final String POR_CONSEJO_Y_TIPO = "/consejo/{id}/por-tipo/{tipoId}";
        public static final String PENDIENTES_POR_CONSEJO = "/consejo/{id}/pendientes";
        public static final String FINALIZAR = "/{id}/finalizar";
        public static final String REPORTE_PERIODO = "/reporte-periodo";
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