package com.proyect.MsSustentacion.config;

import com.proyect.MsSustentacion.model.Error.BusinessRuleException;
import com.proyect.MsSustentacion.model.Error.ErrorResponse;
import com.proyect.MsSustentacion.model.Error.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

        // 1. Manejar una excepción específica (Ej: Recurso no encontrado)
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
                        ResourceNotFoundException ex, WebRequest request) {

                ErrorResponse error = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.NOT_FOUND.value(),
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                ex.getMessage(),
                                request.getDescription(false).replace("uri=", ""));
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        // 2. Manejar una excepción de Regla de Negocio (Ej: BadRequest)
        @ExceptionHandler(BusinessRuleException.class)
        public ResponseEntity<ErrorResponse> handleBusinessRuleException(
                        BusinessRuleException ex, WebRequest request) {

                ErrorResponse error = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST.value(),
                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                ex.getMessage(),
                                request.getDescription(false).replace("uri=", ""));
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        // 3. Manejar cualquier otra excepción no controlada (Error genérico 500)
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleGlobalException(
                        Exception ex, WebRequest request) {

                ErrorResponse error = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                "Ocurrió un error inesperado. Contacte al administrador.",
                                request.getDescription(false).replace("uri=", ""));
                // Opcional: Imprimir el stacktrace en logs aquí
                ex.printStackTrace();

                return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleValidationExceptions(
                        MethodArgumentNotValidException ex, WebRequest request) {

                // Recopilamos todos los errores de los campos en un solo texto
                // Ejemplo resultado: "titulo: no puede estar vacío; fecha: es obligatoria;"
                StringBuilder mensajesErrores = new StringBuilder();

                ex.getBindingResult().getFieldErrors().forEach(error -> {
                        mensajesErrores.append(error.getField())
                                        .append(": ")
                                        .append(error.getDefaultMessage())
                                        .append("; ");
                });

                // Creamos tu ErrorResponse manteniendo tu estructura actual
                ErrorResponse error = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST.value(),
                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                mensajesErrores.toString(), // Ponemos todos los errores aquí
                                request.getDescription(false).replace("uri=", ""));

                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
}