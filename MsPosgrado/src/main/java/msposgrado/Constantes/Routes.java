package msposgrado.Constantes;

/**
 * Clase de constantes que centraliza las rutas base de la API REST del sistema
 * de posgrado.
 *
 * 
 * Permite mantener una única fuente de verdad para las URLs utilizadas por los
 * controladores, facilitando el mantenimiento y evitando duplicación de rutas.
 * 
 */
public class Routes {

    /**
     * Ruta base de la API.
     */
    public static final String API_BASE = "/api";

    /**
     * Ruta para la gestión de asesores.
     */
    public static final String ASESORES = API_BASE + "/asesores";

    /**
     * Ruta para la gestión de documentos.
     */
    public static final String DOCUMENTOS = API_BASE + "/documentos";

    /**
     * Ruta para la gestión de expedientes.
     */
    public static final String EXPEDIENTES = API_BASE + "/expedientes";

    /**
     * Ruta para la relación expediente - jurado.
     */
    public static final String EXPEDIENTE_JURADO = API_BASE + "/expediente-jurado";

    /**
     * Ruta para la gestión de jurados.
     */
    public static final String JURADOS = API_BASE + "/jurados";

    /**
     * Ruta para la gestión de revisiones.
     */
    public static final String REVISIONES = API_BASE + "/revisiones";

    /**
     * Ruta para la gestión de solicitudes.
     */
    public static final String SOLICITUDES = API_BASE + "/solicitudes";

    /**
     * Ruta para la gestión de tesis.
     */
    public static final String TESIS = API_BASE + "/tesis";

    /**
     * Ruta base para los flujos de trámite.
     */
    public static final String TRAMITE = API_BASE + "/tramite";

    /**
     * Ruta base para los flujos de revisión.
     */
    public static final String REVISION = API_BASE + "/revision";

    /**
     * Constructor privado para evitar la instanciación de la clase.
     */
    private Routes() {
        // Evita la creación de instancias
    }
}