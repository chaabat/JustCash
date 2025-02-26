package com.bank.customer_service.exception;

/*
 * Exception pour indiquer que le client n'existe pas
 */
public class CustomerNotFoundException extends RuntimeException {
    /*
     * Constructeur de la classe
     * @param message
     */
    public CustomerNotFoundException(String message) {
        super(message);
    }
    /*
     * Constructeur de la classe
     * @param message
     * @param cause
     */
    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    /*
     * Constructeur de la classe
     * @param id
     */
    public CustomerNotFoundException(Long id) {
        super("Customer not found with id: " + id);
    }
} 