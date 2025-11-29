package com.proyect.MsSustentacion.config;

import com.proyect.MsSustentacion.model.Error.BusinessRuleException;
import com.proyect.MsSustentacion.model.Error.ErrorResponse;
import com.proyect.MsSustentacion.model.Error.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

        // 1. Recurso No Encontrado (404)
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

        // 2. Regla de Negocio (400) - Validaciones manuales del Service
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

        // 3. Validaciones de Annotaciones (@Valid, @NotNull) (400)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleValidationExceptions(
                        MethodArgumentNotValidException ex, WebRequest request) {

                StringBuilder mensajesErrores = new StringBuilder();

                // Recorremos los errores de los campos
                ex.getBindingResult().getAllErrors().forEach(error -> {
                        String campo = ((FieldError) error).getField();
                        String mensaje = error.getDefaultMessage();
                        mensajesErrores.append(campo).append(": ").append(mensaje).append("; ");
                });

                ErrorResponse error = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST.value(),
                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                mensajesErrores.toString(),
                                request.getDescription(false).replace("uri=", ""));

                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        // 4. Error General (500)
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleGlobalException(
                        Exception ex, WebRequest request) {

                ex.printStackTrace(); // Imprime el error en consola para debug

                ErrorResponse error = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                "Ocurrió un error inesperado. Contacte al administrador.",
                                request.getDescription(false).replace("uri=", ""));

                return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
}