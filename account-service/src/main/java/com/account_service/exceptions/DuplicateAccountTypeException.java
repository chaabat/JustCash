package com.account_service.exceptions;

public class DuplicateAccountTypeException extends Exception {
    public DuplicateAccountTypeException(String message) {
        super(message);
    }
} 