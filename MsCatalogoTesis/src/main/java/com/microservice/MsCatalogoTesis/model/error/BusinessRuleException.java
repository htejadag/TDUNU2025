package com.microservice.MsCatalogoTesis.model.error;

/**
 * Excepción para violaciones de reglas de negocio
 */
public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
