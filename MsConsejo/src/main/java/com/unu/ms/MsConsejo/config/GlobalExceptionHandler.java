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

/**
 * Manejador global de excepciones para la API.
 * 
 * Centraliza el manejo de todas las excepciones que puedan ocurrir durante
 * la ejecución de los endpoints REST. Proporciona respuestas consistentes y
 * significativas, logging apropiado y códigos HTTP correctos.
 * 
 * Ventajas:
 * - Consistencia en formatos de error
 * - Logging centralizado de errores
 * - Códigos HTTP adecuados (200, 400, 404, 500, etc.)
 * - Manejo de múltiples tipos de excepciones
 * - Facilita el mantenimiento de la API
 * 
 * Excepciones manejadas:
 * - EntityNotFoundException: Recurso no encontrado (404)
 * - ConstraintViolationException: Violación de restricción (400)
 * - DataAccessException: Error de acceso a datos (500)
 * - HttpMessageNotReadableException: JSON inválido (400)
 * - DataAccessResourceFailureException: Conexión a BD fallida (503)
 * - NumberFormatException: Error de formato numérico (400)
 * - Exception (general): Error inesperado (500)
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Maneja excepciones genéricas no capturadas por otros handlers.
   * 
   * @param ex la excepción general ocurrida
   * @param request la solicitud web que causó la excepción
   * @return ResponseEntity con código 500 (Internal Server Error)
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseBase<Object>> handleGeneralException(Exception ex, WebRequest request) {
    log.error("Error procesando la petición: {}", ex.getMessage());
    ResponseBase<Object> errorResponse = ResponseBase.error(ex.getMessage());
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(errorResponse);
  }

  /**
   * Maneja excepciones cuando una entidad JPA no es encontrada.
   * 
   * @param ex la excepción de entidad no encontrada
   * @return ResponseEntity con código 404 (Not Found)
   */
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ResponseBase<Object>> handleEntityNotFound(EntityNotFoundException ex) {
    log.warn("Entidad no encontrada: {}", ex.getMessage());
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ResponseBase.error(Mensajes.ENTIDAD_NO_ENCONTRADA));
  }

  /**
   * Maneja violaciones de restricciones de la base de datos.
   * Ejemplos: duplicación de valores únicos, violación de clave foránea.
   * 
   * @param ex la excepción de violación de restricción
   * @return ResponseEntity con código 400 (Bad Request)
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ResponseBase<Object>> handleConstraint(ConstraintViolationException ex) {
    log.warn("Violación de restricción: {}", ex.getMessage());
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ResponseBase.error(Mensajes.VIOLACION_RESTRICCION));
  }

  /**
   * Maneja errores genéricos de persistencia en JPA.
   * 
   * @param ex la excepción de persistencia
   * @return ResponseEntity con código 500 (Internal Server Error)
   */
  @ExceptionHandler(PersistenceException.class)
  public ResponseEntity<ResponseBase<Object>> handlePersistence(PersistenceException ex) {
    log.error("Error de persistencia: {}", ex.getMessage(), ex);
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ResponseBase.error(Mensajes.ERROR_PERSISTENCIA));
  }

  /**
   * Maneja excepciones cuando el JSON de la solicitud es mal formado o inválido.
   * 
   * @param ex la excepción de mensaje HTTP no legible
   * @return ResponseEntity con código 400 (Bad Request)
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ResponseBase<Object>> handleUnreadable(HttpMessageNotReadableException ex) {
    log.warn("JSON inválido: {}", ex.getMessage());
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ResponseBase.error(Mensajes.JSON_INVALIDO));
  }

  /**
   * Maneja excepciones cuando hay fallos en la conexión a la base de datos.
   * 
   * @param ex la excepción de fallo en recurso de acceso a datos
   * @return ResponseEntity con código 503 (Service Unavailable)
   */
  @ExceptionHandler(DataAccessResourceFailureException.class)
  public ResponseEntity<ResponseBase<Object>> handleDbConnection(DataAccessResourceFailureException ex) {
    log.error("No se puede conectar a la base de datos: {}", ex.getMessage());
    return ResponseEntity
        .status(HttpStatus.SERVICE_UNAVAILABLE)
        .body(ResponseBase.error(Mensajes.ERROR_CONEXION_BD));
  }

  /**
   * Maneja errores generales de acceso a datos (excepto los más específicos).
   * 
   * @param ex la excepción de acceso a datos
   * @return ResponseEntity con código 500 (Internal Server Error)
   */
  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<ResponseBase<Object>> handleDataAccess(DataAccessException ex) {
    log.error("Error de acceso a datos: {}", ex.getMessage());
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ResponseBase.error(Mensajes.ERROR_ACCESO_DATOS));
  }

  /**
   * Maneja excepciones de conversión de formato numérico.
   * Ocurre cuando se intenta convertir un String a número y falla.
   * 
   * @param ex la excepción de formato numérico inválido
   * @return ResponseEntity con código 400 (Bad Request)
   */
  @ExceptionHandler(NumberFormatException.class)
  public ResponseEntity<ResponseBase<Object>> handleNumberFormat(NumberFormatException ex) {
    log.warn("Error de formato numérico: {}", ex.getMessage());
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ResponseBase.error(Mensajes.FORMATO_NUMERICO_INVALIDO));
  }

}
