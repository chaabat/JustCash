package com.bank.account_service.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.account_service.model.Entity.Account;

/*
 * Repository pour le compte
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
   
    /*
     * find accounts by client id
     * @param : Long clientId
     * @return : List<Account>
     */
    List<Account> findByClientId(Long clientId);
}
