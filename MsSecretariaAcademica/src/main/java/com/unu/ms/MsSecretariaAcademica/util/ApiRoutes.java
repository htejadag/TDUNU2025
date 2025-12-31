package com.unu.ms.MsSecretariaAcademica.util;

public class ApiRoutes {

    public static class Solicitud {

        public static final String BASE = "/api/solicitud";
        public static final String LISTAR = "/listar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String GUARDAR = "/crear";
        public static final String ELIMINAR = "/eliminar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";

        public static final String OBTENER_POR_PERSONA = "/obtenerPorPersona";
        public static final String OBTENER_POR_ESTADO = "/obtenerPorEstado";
        public static final String OBTENER_POR_PERSONA_Y_ESTADO = "/obtenerPorPersonaYEstado";
        public static final String OBTENER_POR_TIPO = "/obtenerPorTipo";
        public static final String OBTENER_POR_TIPO_Y_ESTADO = "/obtenerPorTipoYEstado";
        public static final String OBTENER_POR_FECHA_RANGO = "/obtenerPorFechaRango";

    }

    public static class Resolucion {

        public static final String BASE = "/api/resolucion";
        public static final String LISTAR = "/listar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String GUARDAR = "/crear";
        public static final String ELIMINAR = "/eliminar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";

        public static final String BUSCAR_POR_EXPEDIENTE = "/buscarPorExpediente";
        public static final String BUSCAR_POR_NUMERO = "/buscarPorNumero";
        public static final String BUSCAR_POR_TIPO = "/buscarPorTipo";
        public static final String BUSCAR_POR_ESTADO = "/buscarPorEstado";
        public static final String BUSCAR_POR_TIPO_Y_ESTADO = "/buscarPorTipoYEstado";
        public static final String BUSCAR_POR_APROBADO_EN_SESION = "/buscarPorAprobadoEnSesion";

    }

    public static class Expediente {

        public static final String BASE = "/api/expediente";
        public static final String LISTAR = "/listar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String GUARDAR = "/crear";
        public static final String ELIMINAR = "/eliminar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";

        public static final String BUSCAR_POR_CODIGO = "/buscarPorCodigo";
        public static final String BUSCAR_POR_PERSONA = "/buscarPorPersona";
        public static final String BUSCAR_POR_ESTADO = "/buscarPorEstado";
        public static final String BUSCAR_POR_TIPO_Y_ESTADO = "/buscarPorTipoYEstado";

    }

    public static class ResolucionArticulo {

        public static final String BASE = "/api/resolucion-articulo";
        public static final String LISTAR = "/listar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String GUARDAR = "/crear";
        public static final String ELIMINAR = "/eliminar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";

        public static final String BUSCAR_POR_RESOLUCION = "/buscarPorResolucion";

    }

    public static class Seguimiento {

        public static final String BASE = "/api/seguimiento";
        public static final String LISTAR = "/listar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String GUARDAR = "/crear";
        public static final String ELIMINAR = "/eliminar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";

        public static final String BUSCAR_POR_ENTIDAD = "/buscarPorEntidad";

    }

    public static class Catalogo {

        public static final String BASE = "/api/catalogo";
        public static final String LISTAR = "/listar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String GUARDAR = "/crear";
        public static final String ELIMINAR = "/eliminar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";

        public static final String BUSCAR_POR_CATEGORIA_Y_VALOR = "/buscarPorCategoriaYValor";
        public static final String BUSCAR_POR_CATEGORIA = "/buscarPorCategoria";
        
    }

}