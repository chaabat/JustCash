package com.account_service.dtos;

import com.account_service.entities.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AccountRequestDTO {
    private double initialBalance;
    private AccountType type;
    private Long clientId;
} 