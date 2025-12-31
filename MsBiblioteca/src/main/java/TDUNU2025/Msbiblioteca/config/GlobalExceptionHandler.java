package TDUNU2025.Msbiblioteca.config;

import TDUNU2025.Msbiblioteca.util.Mensaje;
import TDUNU2025.Msbiblioteca.util.ResponseBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseBase<Void>> handleBusinessException(BusinessException ex) {
        log.warn("Excepción de negocio capturada: {}", ex.getMessage());

        return ResponseEntity.badRequest().body(
                new ResponseBase<>(
                        Mensaje.CODE_ERROR,
                        ex.getMessage(),
                        null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase<Void>> handleException(Exception ex) {
        log.error("Error interno del servidor no controlado: ", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseBase<>(
                        Mensaje.CODE_ERROR,
                        "Ocurrió un error interno en el servidor",
                        null));
    }
}