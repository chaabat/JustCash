package com.bank.customer_service.model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*
 * Entité pour le client
 * paramètres:  
 * - id: identifiant du client
 * - name: nom du client
 * - email: email du client
 * - address: adresse du client
 */
@Data
@Entity
@Table(name = "clients")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false,unique = true)
    private String email;
    @Column(name = "address", nullable = false)
    private String address;
}
