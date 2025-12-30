package Ms_Reingresante.Ms_Reingresante.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatriculaBase<T> {
    
    // Indica si la operación fue exitosa
    private boolean success; 
    
    // Mensaje de la operación (éxito o error)
    private String message; 
    
    // Los datos devueltos (List<Response>, Response, etc.)
    private T data; 

    /**
     * Constructor estático para respuestas exitosas (OK) con datos.
     * @param <T> Tipo de datos a devolver.
     * @param data Los datos del resultado.
     * @return Respuesta estándar de éxito.
     */
    public static <T> MatriculaBase<T> ok(T data) {
        return MatriculaBase.<T>builder()
            .success(true)
            .message("Proceso Matrícula, operación exitosa")
            .data(data)
            .build();
    }

    /**
     * Constructor estático para respuestas exitosas (OK) con mensaje y datos.
     * @param <T> Tipo de datos a devolver.
     * @param message Mensaje personalizado.
     * @param data Los datos del resultado.
     * @return Respuesta estándar de éxito con mensaje.
     */
    public static <T> MatriculaBase<T> ok(String message, T data) {
        return MatriculaBase.<T>builder()
            .success(true)
            .message(message)
            .data(data)
            .build();
    }

    /**
     * Constructor estático para respuestas de error.
     * @param <T> Tipo de datos a devolver (generalmente se usa Object o null).
     * @param message Mensaje de error.
     * @return Respuesta estándar de error.
     */
    public static <T> MatriculaBase<T> error(String message) {
        return MatriculaBase.<T>builder()
            .success(false)
            .message(message)
            .build();
    }
}