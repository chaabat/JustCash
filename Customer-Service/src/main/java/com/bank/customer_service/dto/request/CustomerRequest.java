package com.bank.customer_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
/*
 * DTO pour requête d'un client
 * paramètres:
 * - name: nom du client
 * - email: email du client
 * - address: adresse du client
 */
public class CustomerRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Address is required")
    private String address;
}
