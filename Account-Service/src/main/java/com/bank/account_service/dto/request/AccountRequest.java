package com.bank.account_service.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
/*
 * DTO pour requête d'un client
 * paramètres:
 * - solde: solde du compte
 * - type: type de compte (COURANT/EPARGNE)
 * - clientId: identifiant du client
 */
public class AccountRequest {
    @NotNull(message = "Solde is required")
    private Double solde;
    @NotBlank(message = "Type is required")
    private String type;
    @NotNull(message = "ClientId is required")
    private Long clientId;
}
