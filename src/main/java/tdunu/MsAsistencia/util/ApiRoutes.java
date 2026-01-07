package tdunu.MsAsistencia.util;

public class ApiRoutes {

    public static class Programacion {
        public static final String BASE = "/api/programacion";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String ELIMINAR = "/eliminar";
        public static final String BUSCAR_POR_SISTEMA = "/buscarPorSistema";
        public static final String BUSCAR_POR_TIPO = "/buscarPorTipo";
        public static final String BUSCAR_POR_CONTEXTO = "/buscarPorContexto";
    }

    public static class Asistencia {
        public static final String BASE = "/api/asistencia";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String ELIMINAR = "/eliminar";
        public static final String BUSCAR_POR_PROGRAMACION = "/buscarPorProgramacion";
        public static final String BUSCAR_POR_FECHA = "/buscarPorFecha";
    }

    public static class DetalleAsistencia {
        public static final String BASE = "/api/detalle-asistencia";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String ELIMINAR = "/eliminar";
        public static final String BUSCAR_POR_ASISTENCIA = "/buscarPorAsistencia";
        public static final String BUSCAR_POR_ENTIDAD = "/buscarPorEntidad";
        public static final String REGISTRAR_MASIVO = "/registrarMasivo";
    }

    public static class Justificacion {
        public static final String BASE = "/api/justificacion";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String GUARDAR = "/guardar";
        public static final String ACTUALIZAR = "/actualizar";
        public static final String ELIMINAR = "/eliminar";
        public static final String APROBAR = "/aprobar";
        public static final String RECHAZAR = "/rechazar";
        public static final String BUSCAR_PENDIENTES = "/buscarPendientes";
    }

    public static class HistorialAsistencia {
        public static final String BASE = "/api/historial-asistencia";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtenerPorId";
        public static final String BUSCAR_POR_DETALLE = "/buscarPorDetalle";
    }
}
