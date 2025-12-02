package unu.MsGestionDocumental.MsExpediente.config; // <--- ¡VERIFICA QUE ESTÉ BIEN ESCRITO!

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Le dice a Spring que si se lanza esta excepción, devuelva un código 404 (Not Found)
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    // Puedes personalizar el mensaje de error para el usuario
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        // Mensaje que se mostrará: "Expediente no fue encontrado con id : '123'"
        super(String.format("%s no fue encontrado con %s : '%s'", resourceName, fieldName, fieldValue));
    }
}