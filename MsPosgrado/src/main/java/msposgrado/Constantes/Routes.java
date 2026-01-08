package msposgrado.Constantes;

public class Routes {

    public static final String API_BASE = "/api";

    public static final String ASESORES = API_BASE + "/asesores";
    public static final String DOCUMENTOS = API_BASE + "/documentos";
    public static final String EXPEDIENTES = API_BASE + "/expedientes";
    public static final String EXPEDIENTE_JURADO = API_BASE + "/expediente-jurado";
    public static final String JURADOS = API_BASE + "/jurados";
    public static final String REVISIONES = API_BASE + "/revisiones";
    public static final String SOLICITUDES = API_BASE + "/solicitudes";
    public static final String TESIS = API_BASE + "/tesis";

    // Tramites Flows
    public static final String TRAMITE = API_BASE + "/tramite";
    public static final String REVISION = API_BASE + "/revision";

    private Routes() {
        // Private constructor to prevent instantiation
    }
}
