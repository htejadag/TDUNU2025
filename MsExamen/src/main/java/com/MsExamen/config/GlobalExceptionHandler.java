package com.MsExamen.config;

import org.springframework.dao.OptimisticLockingFailureException;
import com.MsExamen.exception.ResourceNotFoundException;

import com.MsExamen.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@Hidden
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse<String>> handleException(Exception e) {
                log.error("Unhandled exception: ", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ApiResponse<>("Internal Server Error: " + e.getMessage(), null));
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiResponse<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
                log.warn("Resource not found: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>(ex.getMessage(), null));
        }

        @ExceptionHandler(OptimisticLockingFailureException.class)
        public ResponseEntity<ApiResponse<String>> handleOptimisticLockingFailureException(
                        OptimisticLockingFailureException ex) {
                log.warn("Concurrency conflict: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(new ApiResponse<>(
                                                "The resource was updated or deleted by another transaction. Please refresh and try again.",
                                                null));
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
                        MethodArgumentNotValidException ex) {
                Map<String, String> errors = new HashMap<>();
                ex.getBindingResult().getFieldErrors()
                                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

                log.warn("Validation failed: {}", errors);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(new ApiResponse<>("Validation Error", errors));
        }

        @ExceptionHandler(NoHandlerFoundException.class)
        public ResponseEntity<ApiResponse<String>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
                log.warn("No handler found: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>("Resource not found: " + ex.getRequestURL(), null));
        }

        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
        public ResponseEntity<ApiResponse<String>> handleHttpRequestMethodNotSupportedException(
                        HttpRequestMethodNotSupportedException ex) {
                log.warn("Method not supported: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                                .body(new ApiResponse<>("Method not allowed: " + ex.getMethod(), null));
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ApiResponse<String>> handleHttpMessageNotReadableException(
                        HttpMessageNotReadableException ex) {
                log.error("Malformed JSON request: ", ex);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(new ApiResponse<>("Malformed JSON request", null));
        }

        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<ApiResponse<String>> handleDataIntegrityViolationException(
                        DataIntegrityViolationException ex) {
                log.error("Database constraint violation: ", ex);
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(new ApiResponse<>("Database error: Constraint violation", null));
        }
}
