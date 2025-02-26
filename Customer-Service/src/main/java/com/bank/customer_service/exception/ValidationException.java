package com.bank.customer_service.exception;

import java.util.Map;

/*
 * Exception pour les exceptions de validation
 */
public class ValidationException extends RuntimeException {
    private final Map<String, String> errors;

    /*
     * Constructeur de la classe
     * @param message
     * @param errors
     */
    public ValidationException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }
    
    public Map<String, String> getErrors() {
        return errors;
    }
} 