package com.bank.account_service.exception;

/*
 * Exception pour le compte déjà existant
 */
public class AccountAlreadyExistsException extends RuntimeException {
    /*
     * Constructeur de la classe
     * @param message
     */
    public AccountAlreadyExistsException(String message) {
        super(message);
    }
    /*
     * Constructeur de la classe
     * @param message
     * @param cause
     */ 
    public AccountAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    /*
     * Exception pour le compte déjà existant avec le numéro de compte
     * @param accountNumber
     * @return AccountAlreadyExistsException
     */ 
    public static AccountAlreadyExistsException withAccountNumber(String accountNumber) {
        return new AccountAlreadyExistsException("Account already exists with account number: " + accountNumber);
    }
} 