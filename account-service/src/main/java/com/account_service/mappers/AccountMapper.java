package com.account_service.mappers;

import com.account_service.dtos.AccountResponseDTO;
import com.account_service.entities.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountResponseDTO toAccountResponseDTO(Account account) {
        return AccountResponseDTO.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .type(account.getType())
                .clientId(account.getClientId())
                .createdAt(account.getCreatedAt())
                .build();
    }
} 