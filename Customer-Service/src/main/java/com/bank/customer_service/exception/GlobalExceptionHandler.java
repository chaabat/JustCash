package com.bank.customer_service.exception;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/*
 * Gestionnaire global des exceptions
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /*
     * Logger pour les exceptions
     */
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*
     * Exception pour le client non trouvé
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(
            CustomerNotFoundException ex, WebRequest request) {
        logger.error("Customer not found exception: ", ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /*
     * Exception pour le client déjà existant
     */
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCustomerAlreadyExistsException(
            CustomerAlreadyExistsException ex, WebRequest request) {
        logger.error("Customer already exists exception: ", ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /*
     * Exception pour les exceptions de validation
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        logger.error("Validation exception: ", ex);
        String errorMessage = ex.getBindingResult().getFieldErrors().isEmpty() ? 
                "Validation failed" : 
                ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Validation failed: " + errorMessage)
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /*
     * Exception pour les exceptions de violation de contrainte
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {
        logger.error("Constraint violation exception: ", ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Validation failed: " + ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /*
     * Exception pour les exceptions de violation de contrainte
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex, WebRequest request) {
        logger.error("Data integrity violation exception: ", ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message("Database error: " + ex.getMostSpecificCause().getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /*
     * Exception pour les exceptions non prévues
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(
            Exception ex, WebRequest request) {
        logger.error("Unknown error occurred: ", ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("An unexpected error occurred")
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
} 