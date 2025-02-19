package com.account_service.repositories;

import com.account_service.entities.Account;
import com.account_service.entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByClientId(Long clientId);
    boolean existsByClientIdAndType(Long clientId, AccountType type);
} 