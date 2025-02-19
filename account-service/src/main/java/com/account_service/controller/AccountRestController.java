package com.account_service.controller;

import com.account_service.dtos.AccountRequestDTO;
import com.account_service.dtos.AccountResponseDTO;
import com.account_service.exceptions.AccountNotFoundException;
import com.account_service.exceptions.CustomerNotFoundException;
import com.account_service.exceptions.DuplicateAccountTypeException;
import com.account_service.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountRestController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        try {
            return ResponseEntity.ok(accountService.createAccount(accountRequestDTO));
        } catch (CustomerNotFoundException | DuplicateAccountTypeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccount(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(accountService.getAccount(id));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AccountResponseDTO>> getAccountsByCustomer(@PathVariable Long customerId) {
        try {
            return ResponseEntity.ok(accountService.getAccountsByCustomer(customerId));
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
} 