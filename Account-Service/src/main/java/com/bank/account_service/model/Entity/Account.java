package com.bank.account_service.model.Entity;

import com.bank.account_service.model.enums.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*      
 * Entité pour le compte        
            * paramètres:  
            * - id: identifiant du compte
            * - solde: solde du compte
            * - type: type de compte (COURANT/EPARGNE)
            * - clientId: identifiant du client
 */
@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "solde", nullable = false)
    private Double solde;
    @NotNull(message = "Le type de compte est requis.")
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Column(name = "clientId", nullable = false)
    private Long clientId;
}