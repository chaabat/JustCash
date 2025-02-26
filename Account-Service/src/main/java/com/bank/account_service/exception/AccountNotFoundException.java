package com.bank.account_service.exception;

/*
 * Exception pour le compte non trouvé
 */
public class AccountNotFoundException extends RuntimeException {
    /*
     * Constructeur de la classe
     * @param message
     */
    public AccountNotFoundException(String message) {
        super(message);
    }
    /*
     * Constructeur de la classe
     * @param message
     * @param cause
     */ 
    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    /*
     * Exception pour le compte non trouvé avec l'id
     * @param id
     * @return AccountNotFoundException
     */ 
    public AccountNotFoundException(Long id) {
        super("Account not found with id: " + id);
    }
} 