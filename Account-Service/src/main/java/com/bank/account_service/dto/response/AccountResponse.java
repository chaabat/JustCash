package com.bank.account_service.dto.response;

import com.bank.account_service.model.enums.AccountType;

import lombok.Data;

@Data
/*
 * DTO pour réponse d'un client
 * paramètres:
 * - id: identifiant du client
 * - solde: solde du compte
 * - type: type de compte (COURANT/EPARGNE)
 * - clientId: identifiant du client
 */
public class AccountResponse {
    private Long id;
    private Double solde;
    private AccountType type;
    private Long clientId;
}
