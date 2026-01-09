package msposgrado.Exception;

import java.time.LocalDateTime;

/**
 * Modelo estándar de respuesta para el manejo de errores en la API.
 *
 * <p>
 * Esta clase se utiliza para estructurar las respuestas de error
 * que se devuelven al cliente cuando ocurre una excepción controlada
 * o no controlada dentro del sistema.
 * </p>
 *
 * <p>
 * Permite enviar información clara y consistente sobre el error,
 * facilitando el diagnóstico tanto para el frontend como para
 * el desarrollador.
 * </p>
 */
public class ErrorResponse {

    /**
     * Fecha y hora en la que ocurrió el error.
     */
    private LocalDateTime timestamp;

    /**
     * Código de estado HTTP asociado al error.
     */
    private int status;

    /**
     * Descripción corta del tipo de error.
     */
    private String error;

    /**
     * Mensaje detallado del error.
     */
    private String message;

    /**
     * Ruta del endpoint donde ocurrió el error.
     */
    private String path;

    /**
     * Constructor vacío requerido para serialización.
     */
    public ErrorResponse() {
    }

    /**
     * Constructor completo para crear una respuesta de error detallada.
     *
     * @param timestamp fecha y hora del error
     * @param status código de estado HTTP
     * @param error tipo de error
     * @param message mensaje descriptivo del error
     * @param path ruta donde ocurrió el error
     */
    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}