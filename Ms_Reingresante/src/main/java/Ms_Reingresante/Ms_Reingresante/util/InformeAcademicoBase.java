package Ms_Reingresante.Ms_Reingresante.util;

// Se define como clase final para evitar herencia
public final class ApiRoutes {

    // Define las operaciones CRUD comunes
    public static final String LISTAR = "/listar";
    public static final String OBTENER_POR_ID = "/obtenerPorId";
    public static final String GUARDAR = "/guardar";
    public static final String ELIMINAR = "/eliminar";
    
    // --- RUTAS BASE DEL MICROSERVICIO ---
    
    public static final class ProcesoReingresante {
        // Ruta base: /api/v1/proceso
        public static final String BASE = "/api/v1/proceso";
        public static final String LISTAR = ApiRoutes.LISTAR;
        public static final String OBTENER_POR_ID = ApiRoutes.OBTENER_POR_ID;
        public static final String GUARDAR = ApiRoutes.GUARDAR;
        public static final String ELIMINAR = ApiRoutes.ELIMINAR;
    }

    public static final class FichaNoAdeudo {
        // Ruta base: /api/v1/ficha-no-adeudo
        public static final String BASE = "/api/v1/ficha-no-adeudo";
        public static final String LISTAR = ApiRoutes.LISTAR;
        public static final String OBTENER_POR_ID = ApiRoutes.OBTENER_POR_ID;
        // Se puede usar "GENERAR" en lugar de "GUARDAR" para reflejar mejor la acción de negocio
        public static final String GUARDAR = "/generar"; 
        public static final String APROBAR = "/aprobar"; 
    }

    public static final class InformeAcademico {
        // Ruta base: /api/v1/informe-academico
        public static final String BASE = "/api/v1/informe-academico";
        public static final String LISTAR = ApiRoutes.LISTAR;
        public static final String OBTENER_POR_ID = ApiRoutes.OBTENER_POR_ID;
        public static final String GUARDAR = "/generar"; 
    }
}