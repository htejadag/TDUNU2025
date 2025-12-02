package unu.MsGestionDocumental.MsExpediente.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        String mensajeError = ex.getMessage();

        return new ResponseEntity<>(mensajeError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        String mensajeError = "Ocurrió un error inesperado: " + ex.getMessage();
        return new ResponseEntity<>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
    }    

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String mensajeError = "El formato de los datos es incorrecto. "
                + "Verifique que los campos numéricos contengan solo números.";
        
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) ex.getCause();
            mensajeError = String.format(
                    "Valor '%s' no es válido para el campo '%s'. Tipo esperado: %s.",
                    ife.getValue(),
                    ife.getPath().get(0).getFieldName(),
                    ife.getTargetType().getSimpleName());
        }

        return new ResponseEntity<>(mensajeError, HttpStatus.BAD_REQUEST); 
    }
}