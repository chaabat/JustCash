package com.account_service.services;

import com.account_service.clients.CustomerRestClient;
import com.account_service.dtos.AccountRequestDTO;
import com.account_service.dtos.AccountResponseDTO;
import com.account_service.dtos.CustomerDTO;
import com.account_service.entities.Account;
import com.account_service.exceptions.AccountNotFoundException;
import com.account_service.exceptions.CustomerNotFoundException;
import com.account_service.exceptions.DuplicateAccountTypeException;
import com.account_service.mappers.AccountMapper;
import com.account_service.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CustomerRestClient customerRestClient;

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) throws CustomerNotFoundException, DuplicateAccountTypeException {
        // Vérifier si le client existe
        try {
            CustomerDTO customer = customerRestClient.getCustomerById(accountRequestDTO.getClientId());
        } catch (RestClientException e) {
            throw new CustomerNotFoundException("Customer not found with ID: " + accountRequestDTO.getClientId());
        }

        // Vérifier si le client a déjà un compte du même type
        if (accountRepository.existsByClientIdAndType(accountRequestDTO.getClientId(), accountRequestDTO.getType())) {
            throw new DuplicateAccountTypeException("Customer already has an account of type: " + accountRequestDTO.getType());
        }

        Account account = Account.builder()
                .balance(accountRequestDTO.getInitialBalance())
                .type(accountRequestDTO.getType())
                .clientId(accountRequestDTO.getClientId())
                .createdAt(LocalDateTime.now().toString())
                .build();

        Account savedAccount = accountRepository.save(account);
        return accountMapper.toAccountResponseDTO(savedAccount);
    }

    @Override
    public AccountResponseDTO getAccount(Long id) throws AccountNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + id));
        
        AccountResponseDTO accountResponseDTO = accountMapper.toAccountResponseDTO(account);
        try {
            CustomerDTO customer = customerRestClient.getCustomerById(account.getClientId());
            accountResponseDTO.setCustomer(customer);
        } catch (RestClientException e) {
            // Log the error but don't throw exception as the account exists
        }
        
        return accountResponseDTO;
    }

    @Override
    public List<AccountResponseDTO> getAccountsByCustomer(Long customerId) throws CustomerNotFoundException {
        try {
            CustomerDTO customer = customerRestClient.getCustomerById(customerId);
            List<Account> accounts = accountRepository.findByClientId(customerId);
            return accounts.stream()
                    .map(account -> {
                        AccountResponseDTO dto = accountMapper.toAccountResponseDTO(account);
                        dto.setCustomer(customer);
                        return dto;
                    })
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
        }
    }

    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(account -> {
                    AccountResponseDTO dto = accountMapper.toAccountResponseDTO(account);
                    try {
                        CustomerDTO customer = customerRestClient.getCustomerById(account.getClientId());
                        dto.setCustomer(customer);
                    } catch (RestClientException e) {
                        // Log the error but continue processing
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }
} 