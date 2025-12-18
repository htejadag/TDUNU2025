package com.unu.ms.MsConsejo.config;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.unu.ms.MsConsejo.util.Mensajes;
import com.unu.ms.MsConsejo.util.ResponseBase;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;

import lombok.extern.slf4j.Slf4j;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  // Error General
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseBase<Object>> handleGeneralException(Exception ex, WebRequest request) {
    log.error("Error procesando la petición: {}", ex.getMessage());
    ResponseBase<Object> errorResponse = ResponseBase.error(ex.getMessage());
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(errorResponse);
  }

  // Entidad no encontrada (JPA)
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ResponseBase<Object>> handleEntityNotFound(EntityNotFoundException ex) {
    log.warn("Entidad no encontrada: {}", ex.getMessage());
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ResponseBase.error(Mensajes.ENTIDAD_NO_ENCONTRADA));
  }

  // Error por restricciones de BD
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ResponseBase<Object>> handleConstraint(ConstraintViolationException ex) {
    log.warn("Violación de restricción: {}", ex.getMessage());
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ResponseBase.error(Mensajes.VIOLACION_RESTRICCION));
  }

  // Errores genéricos de persistencia
  @ExceptionHandler(PersistenceException.class)
  public ResponseEntity<ResponseBase<Object>> handlePersistence(PersistenceException ex) {
    log.error("Error de persistencia: {}", ex.getMessage(), ex);
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ResponseBase.error(Mensajes.ERROR_PERSISTENCIA));
  }

  // JSON mal formado
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ResponseBase<Object>> handleUnreadable(HttpMessageNotReadableException ex) {
    log.warn("JSON inválido: {}", ex.getMessage());
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ResponseBase.error(Mensajes.JSON_INVALIDO));
  }

  // Fallos de conexión a la BD
  @ExceptionHandler(DataAccessResourceFailureException.class)
  public ResponseEntity<ResponseBase<Object>> handleDbConnection(DataAccessResourceFailureException ex) {
    log.error("No se puede conectar a la base de datos: {}", ex.getMessage());
    return ResponseEntity
        .status(HttpStatus.SERVICE_UNAVAILABLE)
        .body(ResponseBase.error(Mensajes.ERROR_CONEXION_BD));
  }

  // Errores generales de acceso a datos
  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<ResponseBase<Object>> handleDataAccess(DataAccessException ex) {
    log.error("Error de acceso a datos: {}", ex.getMessage());
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ResponseBase.error(Mensajes.ERROR_ACCESO_DATOS));
  }

  // Error de conversión numérica
  @ExceptionHandler(NumberFormatException.class)
  public ResponseEntity<ResponseBase<Object>> handleNumberFormat(NumberFormatException ex) {
    log.warn("Error de formato numérico: {}", ex.getMessage());
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ResponseBase.error(Mensajes.FORMATO_NUMERICO_INVALIDO));
  }

}
