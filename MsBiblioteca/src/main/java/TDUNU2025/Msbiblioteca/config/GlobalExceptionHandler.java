<<<<<<< HEAD
package tdunu2025.msbiblioteca.config;
=======
package TDUNU2025.Msbiblioteca.config;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import TDUNU2025.Msbiblioteca.exception.ResourceNotFoundException; // Importar esto
import TDUNU2025.Msbiblioteca.util.Mensaje;
import TDUNU2025.Msbiblioteca.util.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

<<<<<<< HEAD
import tdunu2025.msbiblioteca.exception.ResourceNotFoundException;
import tdunu2025.msbiblioteca.util.Mensaje;
import tdunu2025.msbiblioteca.util.ResponseBase;

=======
>>>>>>> parent of 852a09b (Actualizacion de ruta)
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Este captura TU excepción personalizada (cuando no encuentra algo)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseBase<Void>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ResponseBase<Void> response = new ResponseBase<>(
                Mensaje.CODE_ERROR,      // O puedes crear un CODE_NOT_FOUND = "404" en Mensaje
                ex.getMessage(),         // "Libro no encontrado con id: 5"
                null
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    // ---------------------------

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase<Void>> handleAllExceptions(Exception ex) {
        ResponseBase<Void> response = new ResponseBase<>(
                "500", // Código de error interno
                "Ocurrió un error interno: " + ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}