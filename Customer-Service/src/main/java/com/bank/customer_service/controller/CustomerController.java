package com.bank.customer_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.bank.customer_service.dto.request.CustomerRequest;
import com.bank.customer_service.dto.response.CustomerResponse;
import com.bank.customer_service.service.CustomerService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.ws.rs.DefaultValue;

@RestController
@RequestMapping("api/customers")
@OpenAPIDefinition(info = @Info(title = "Customer Service API", version = "1.0", description = "API pour la gestion des clients"))
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Créer un nouveau client", description = "Crée un nouveau client avec les informations fournies")
    @Schema(implementation = CustomerRequest.class)
    @ApiResponse(responseCode = "201", description = "Client créé avec succès")
    @ApiResponse(responseCode = "409", description = "Customer already exists with email: xxxxx@example.com")
    @ApiResponse(responseCode = "400", description = "Validation failed: Invalid email format")
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request) {
        CustomerResponse customerResponse = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
    }

    @Operation(summary = "Récupérer tous les clients", description = "Récupère tous les clients avec pagination")
    @ApiResponse(responseCode = "200", description = "Clients récupérés avec succès")
    @ApiResponse(responseCode = "404", description = "Aucun client trouvé")
    @GetMapping
    public Page<CustomerResponse> getAllCustomers(Pageable pageable, @RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {

            return customerService.searchCustomersByName(name, pageable);
        }

        return customerService.getAllCustomers(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un client par son ID", description = "Récupère un client par son ID")
    @ApiResponse(responseCode = "200", description = "Client récupéré avec succès")
    @ApiResponse(responseCode = "404", description = "Client non trouvé")
    public CustomerResponse getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un client", description = "Met à jour les informations d'un client existant")
    @ApiResponse(responseCode = "200", description = "Client mis à jour avec succès")
    @ApiResponse(responseCode = "404", description = "Client non trouvé")
    @ApiResponse(responseCode = "409", description = "L'email est déjà utilisé par un autre client")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequest request) {
        CustomerResponse updatedCustomer = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client", description = "Supprime un client par son ID")
    @ApiResponse(responseCode = "204", description = "Client supprimé avec succès")
    @ApiResponse(responseCode = "404", description = "Client non trouvé")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}