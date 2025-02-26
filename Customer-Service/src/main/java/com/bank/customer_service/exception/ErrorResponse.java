package com.bank.customer_service.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
/*
 * DTO pour la réponse d'erreur
 * paramètres:
 * - timestamp: date et heure de l'erreur
 * - status: code de statut de l'erreur
 * - error: message d'erreur
 */
public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
} 