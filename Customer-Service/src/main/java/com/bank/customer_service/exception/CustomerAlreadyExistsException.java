package com.bank.customer_service.exception;

/*
 * Exception pour indiquer que le client existe déjà
 */ 
public class CustomerAlreadyExistsException extends RuntimeException {
    /*
     * Constructeur de la classe
     * @param message
     */
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
    /*
     * Constructeur de la classe
     * @param message
     * @param cause
     */
    public CustomerAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public static CustomerAlreadyExistsException withEmail(String email) {
        return new CustomerAlreadyExistsException("Customer already exists with email: " + email);
    }
} 