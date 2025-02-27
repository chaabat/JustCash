package com.bank.account_service.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.account_service.model.Entity.Account;

/*
 * Repository pour le compte
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    /*
     * find accounts by client id with pagination
     * 
     * @param clientId
     * 
     * @param pageable
     * 
     * @return Page<Account>
     */
    Page<Account> findByClientId(Long clientId, Pageable pageable);

    /*
     * find all accounts by client id without pagination
     * 
     * @param clientId
     * 
     * @return List<Account>
     */
    List<Account> findAllByClientId(Long clientId);
}
