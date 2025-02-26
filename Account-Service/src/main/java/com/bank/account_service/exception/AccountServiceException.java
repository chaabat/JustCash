package com.bank.account_service.exception;

/*
 * Exception pour l'exception de service de compte
 */
public class AccountServiceException extends RuntimeException {
    /*
     * Constructeur de la classe
     * @param message
     */
    public AccountServiceException(String message) {
        super(message);
    }
    /*
     * Constructeur de la classe
     * @param message
     * @param cause
     */
    public AccountServiceException(String message, Throwable cause) {
        super(message, cause);
    }
} 