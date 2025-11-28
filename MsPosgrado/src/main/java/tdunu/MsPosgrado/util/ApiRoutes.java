package tdunu.MsPosgrado.util;

public class ApiRoutes {

    // --- RUTA BASE DEL MICROSERVICIO ---
    // Usada por todos los controladores dentro de MsPosgrado
    public static final String MS = "/api/msposgrado";

    // --- RUTAS ESPECÍFICAS DE LA ENTIDAD ASESOR (Etapa A) ---
    public static class Asesor {
        // Ruta base para el AsesorController (MS_BASE + /asesor)
        public static final String BASE = MS + "/asesor";

        // Operaciones CRUD
        public static final String OBTENER_POR_ID = "/{id}"; // GET /api/msposgrado/asesor/{id}
        public static final String GUARDAR = ""; // POST /api/msposgrado/asesor
        public static final String ACTUALIZAR = "/{id}"; // PUT /api/msposgrado/asesor/{id}
        public static final String ELIMINAR = "/{id}"; // DELETE /api/msposgrado/asesor/{id}
    }
}