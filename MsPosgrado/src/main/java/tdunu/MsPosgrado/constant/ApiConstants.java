package tdunu.MsPosgrado.constant;

public final class ApiConstants {

    private ApiConstants() {}

    // --- Rutas Base ---
    public static final String API_BASE = "/api"; // Sin versiones, limpio
    
    // --- Rutas de Controladores ---
    // Resultado final: "/api/estudiante-proceso"
    public static final String ESTUDIANTE_PROCESO_URI = API_BASE + "/estudiante-proceso";
    
    // --- Parámetros comunes ---
    public static final String ID_PARAM = "/{id}";
}