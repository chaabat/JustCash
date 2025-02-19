package com.account_service.dtos;

import com.account_service.entities.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AccountResponseDTO {
    private Long id;
    private double balance;
    private AccountType type;
    private Long clientId;
    private String createdAt;
    private CustomerDTO customer;
} 