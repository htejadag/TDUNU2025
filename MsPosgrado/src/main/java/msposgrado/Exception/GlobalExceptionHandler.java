package msposgrado.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global de excepciones para la aplicación.
 *
 * <p>
 * Esta clase centraliza el tratamiento de errores lanzados por los
 * controladores REST, garantizando respuestas consistentes y
 * estructuradas hacia el cliente.
 * </p>
 *
 * <p>
 * Utiliza {@link RestControllerAdvice} para interceptar excepciones
 * de manera transversal en toda la API.
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja las excepciones de validación provocadas por anotaciones
     * como {@code @NotNull}, {@code @NotBlank}, {@code @Size}, etc.
     *
     * <p>
     * Extrae los errores de cada campo validado y los devuelve en una
     * respuesta estructurada con código HTTP 400 (BAD REQUEST).
     * </p>
     *
     * @param ex excepción lanzada durante la validación de argumentos
     * @param request información de la solicitud web
     * @return respuesta con el detalle de los errores de validación
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            WebRequest request
    ) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                errors.toString(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja cualquier excepción no controlada dentro de la aplicación.
     *
     * <p>
     * Este método actúa como un fallback para errores inesperados,
     * devolviendo una respuesta con código HTTP 500 (INTERNAL SERVER ERROR).
     * </p>
     *
     * @param ex excepción capturada
     * @param request información de la solicitud web
     * @return respuesta estándar de error interno
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex,
            WebRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}