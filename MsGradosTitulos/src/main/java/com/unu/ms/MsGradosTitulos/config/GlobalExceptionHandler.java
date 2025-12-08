package com.unu.ms.MsGradosTitulos.config;

import com.unu.ms.MsGradosTitulos.util.ResponseBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseBase<String>> handleIllegalArgument(IllegalArgumentException ex) {
        log.error("Error de argumento ilegal: {}", ex.getMessage(), ex);
        return ResponseEntity
            .badRequest()
            .body(ResponseBase.error("Error en los datos proporcionados: " + ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseBase<String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        log.error("Error de integridad de datos: {}", ex.getMessage(), ex);
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ResponseBase.error("Error de integridad de datos. Verifique las relaciones entre entidades."));
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ResponseBase<String>> handleSQLException(SQLException ex) {
        log.error("Error SQL: {}", ex.getMessage(), ex);
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ResponseBase.error("Error en la base de datos: " + ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase<String>> handleGeneralException(Exception ex) {
        log.error("Error inesperado: {}", ex.getMessage(), ex);
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ResponseBase.error("Error interno del servidor: " + ex.getMessage()));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseBase<String>> handleNullPointer(NullPointerException ex) {
        log.error("Error NullPointer: {}", ex.getMessage(), ex);
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ResponseBase.error("Error: Datos no encontrados o referencia nula"));
    }
}
