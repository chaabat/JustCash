package com.bank.customer_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.customer_service.model.Entity.Customer;

/*
 * Repository pour le client
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /*
     * Vérifie si un client existe déjà avec un email donné
     * @param email
     * @return boolean
     */
    boolean existsByEmail(String email);
}
