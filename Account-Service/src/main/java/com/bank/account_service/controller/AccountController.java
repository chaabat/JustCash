package com.bank.account_service.controller;

import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account_service.dto.request.AccountRequest;
import com.bank.account_service.dto.response.AccountResponse;
import com.bank.account_service.service.AccountService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/accounts")
@OpenAPIDefinition(info = @Info(title = "Account Service API", version = "1.0", description = "API pour la gestion des comptes"))
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Operation(summary = "Créer un nouveau compte", description = "Crée un nouveau compte avec les informations fournies")
    @Schema(implementation = AccountRequest.class)
    @ApiResponse(responseCode = "201", description = "Compte créé avec succès")
    @ApiResponse(responseCode = "409", description = "Compte déjà existant")
    @ApiResponse(responseCode = "400", description = "Validation failed: Invalid email format")
    
    @PostMapping
    public AccountResponse createAccount(@Valid @RequestBody AccountRequest request) {
        return accountService.createAccount(request);
    }


    @GetMapping
    @Operation(summary = "Récupérer tous les comptes", description = "Récupère tous les comptes")
    @ApiResponse(responseCode = "200", description = "Comptes récupérés avec succès")
    public List<AccountResponse> getAllAccounts() {
        return accountService.getAllAccounts();
    }



    @Operation(summary = "Récupérer un compte par son ID", description = "Récupère un compte par son ID")
    @ApiResponse(responseCode = "200", description = "Compte récupéré avec succès")
    @ApiResponse(responseCode = "404", description = "Compte non trouvé")
    @GetMapping("/{id}")
    public AccountResponse getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @Operation(summary = "Récupérer tous les comptes d'un client", description = "Récupère tous les comptes d'un client par son ID")
    @ApiResponse(responseCode = "200", description = "Comptes récupérés avec succès")
    @ApiResponse(responseCode = "404", description = "Aucun compte trouvé")
    @GetMapping("/client/{clientId}")
    public List<AccountResponse> getAccountsByClientId(@PathVariable Long clientId) {
        return accountService.getAccountsByClientId(clientId);
    }

    @Operation(summary = "Supprimer un compte par son ID", description = "Supprime un compte par son ID")
    @ApiResponse(responseCode = "200", description = "Compte supprimé avec succès")
    @ApiResponse(responseCode = "404", description = "Compte non trouvé")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
       accountService.deleteAccount(id);
       return ResponseEntity.status(HttpStatus.SC_NO_CONTENT).body("Account deleted successfully");
    }

    @Operation(summary = "Supprimer tous les comptes d'un client par son ID", description = "Supprime tous les comptes d'un client par son ID")
    @ApiResponse(responseCode = "200", description = "Comptes supprimés avec succès")
    @ApiResponse(responseCode = "404", description = "Aucun compte trouvé")
    @DeleteMapping("/delete-by-client-id/{clientId}")
    public ResponseEntity<String> deleteAccountByClientId(@PathVariable Long clientId) {
        accountService.deleteAccountByClientId(clientId);
        return ResponseEntity.status(HttpStatus.SC_NO_CONTENT).body("Accounts deleted successfully");
    }

    
}
