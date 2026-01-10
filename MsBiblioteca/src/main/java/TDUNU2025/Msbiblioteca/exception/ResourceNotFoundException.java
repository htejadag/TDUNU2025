<<<<<<< HEAD
package tdunu2025.msbiblioteca.exception;
=======
package TDUNU2025.Msbiblioteca.exception;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Creamos una excepción simple para cuando no se encuentra un recurso.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s no encontrado con %s : '%s'", resourceName, fieldName, fieldValue));
    }
}