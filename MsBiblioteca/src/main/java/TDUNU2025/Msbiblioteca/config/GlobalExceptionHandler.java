package TDUNU2025.Msbiblioteca.config;

import TDUNU2025.Msbiblioteca.util.Mensaje;
import TDUNU2025.Msbiblioteca.util.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase<Void>> handleAllExceptions(Exception ex) {
        ResponseBase<Void> response = new ResponseBase<>(
                Mensaje.CODE_ERROR,
                "Ocurrió un error interno: " + ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}