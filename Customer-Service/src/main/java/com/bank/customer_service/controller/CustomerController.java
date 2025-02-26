package com.bank.customer_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.customer_service.dto.request.CustomerRequest;
import com.bank.customer_service.dto.response.CustomerResponse;
import com.bank.customer_service.service.CustomerService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;


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
    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {
        return customerService.getAllCustomers(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un client par son ID", description = "Récupère un client par son ID")
    @ApiResponse(responseCode = "200", description = "Client récupéré avec succès")
    @ApiResponse(responseCode = "404", description = "Client non trouvé")
    public CustomerResponse getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client par son ID", description = "Supprime un client par son ID")
    @ApiResponse(responseCode = "200", description = "Client supprimé avec succès")
    @ApiResponse(responseCode = "404", description = "Client non trouvé")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Customer deleted successfully");
    }

}