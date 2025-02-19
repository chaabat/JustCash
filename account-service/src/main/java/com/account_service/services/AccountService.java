package com.account_service.services;

import com.account_service.dtos.AccountRequestDTO;
import com.account_service.dtos.AccountResponseDTO;
import com.account_service.entities.Account;
import com.account_service.exceptions.AccountNotFoundException;
import com.account_service.exceptions.CustomerNotFoundException;
import com.account_service.exceptions.DuplicateAccountTypeException;

import java.util.List;

public interface AccountService {
    AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) throws CustomerNotFoundException, DuplicateAccountTypeException;
    AccountResponseDTO getAccount(Long id) throws AccountNotFoundException;
    List<AccountResponseDTO> getAccountsByCustomer(Long customerId) throws CustomerNotFoundException;
    List<AccountResponseDTO> getAllAccounts();
} 