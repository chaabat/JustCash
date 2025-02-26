package com.bank.customer_service.dto.response;

import lombok.Data;

@Data
/*
 * DTO pour réponse d'un client
 * paramètres:
 * - id: identifiant du client
 * - name: nom du client
 * - email: email du client
 * - address: adresse du client
 */
public class CustomerResponse {
    private Long id;
    private String name;
    private String email;
    private String address;
}
