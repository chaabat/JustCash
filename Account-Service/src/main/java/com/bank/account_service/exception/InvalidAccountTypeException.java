package com.bank.account_service.exception;

/*
 * Exception pour le type de compte invalide
 */
public class InvalidAccountTypeException extends RuntimeException {
    /*
     * Constructeur de la classe
     * @param message
     */
    public InvalidAccountTypeException(String message) {
        super(message);
    }
} 