package com.bank.account_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.bank.account_service.dto.request.AccountRequest;
import com.bank.account_service.dto.response.AccountResponse;
import com.bank.account_service.exception.AccountAlreadyExistsException;
import com.bank.account_service.exception.AccountNotFoundException;
import com.bank.account_service.exception.AccountServiceException;
import com.bank.account_service.exception.InvalidAccountTypeException;
import com.bank.account_service.mapper.AccountMapper;
import com.bank.account_service.model.Entity.Account;
import com.bank.account_service.repository.AccountRepository;
import com.bank.account_service.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountMapper accountMapper;


/*
 * create account
 * @param : AccountRequest request
 * @return : AccountResponse
 */
    @Override
    public AccountResponse createAccount(AccountRequest request) {
        if (!request.getType().equals("COURANT") && !request.getType().equals("EPARGNE")) {
            throw new InvalidAccountTypeException("Invalid account type: " + request.getType());
        }
        
        // Un client peut avoir un seul compte courant et/ou un seul compte d'épargne
        List<Account> customerAccounts = accountRepository.findByClientId(request.getClientId());
        if(customerAccounts.size() == 2 ){
            throw new AccountAlreadyExistsException("Client already has 2 accounts");
        }else if(customerAccounts.size() == 1){
            if(customerAccounts.get(0).getType().toString().equals(request.getType())){
                throw new AccountAlreadyExistsException("Client already has a " + request.getType() + " account");
            }
        }
        
        
        
        // Verify client existence
        System.out.println("request.getClientId() : " + request.getClientId());
        RestTemplate restTemplate = new RestTemplate();
        String clientUrl = "http://localhost:8080/customer-service/api/customers/" + request.getClientId();
        try {
            restTemplate.getForEntity(clientUrl, String.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new AccountNotFoundException("Client not found");
        } catch (HttpServerErrorException e) {
            throw new AccountServiceException("Customer service error: " + e.getMessage());
        }

        Account account = accountMapper.toEntity(request);
        accountRepository.save(account);
        return accountMapper.toResponse(account);
    }

    /*
     * get all accounts
     * @return : List<AccountResponse>
     */
    @Override
    public List<AccountResponse> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(accountMapper::toResponse).collect(Collectors.toList());
    }

    /*
     * get account by id
     * @param : Long id
     * @return : AccountResponse
     */
    @Override
    public AccountResponse getAccountById(Long id) {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return accountMapper.toResponse(account);
    }
  

    /*
     * get accounts by client id
     * @param : Long clientId
     * @return : List<AccountResponse>
     */
    @Override
    public List<AccountResponse> getAccountsByClientId(Long clientId) {
        List<Account> accounts = accountRepository.findByClientId(clientId);
        if(accounts.isEmpty()){
            throw new AccountNotFoundException("No accounts found for client id: " + clientId);
        }
        return accounts.stream().map(accountMapper::toResponse).collect(Collectors.toList());
    }

    /*
     * delete account
     * @param : Long id of the account to delete
     * @return : boolean
     */
    @Override
    public boolean deleteAccount(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException("Account not found");
        }   
        accountRepository.deleteById(id);
        return true;
    } 
    
    /*
     * delete all accounts by client id
     * @param : Long clientId
     * @return : boolean
     */
    @Override
    public boolean deleteAccountByClientId(Long clientId) {
        List<Account> accounts = accountRepository.findByClientId(clientId);
        if(accounts.isEmpty()){
            return true;
        }
        accountRepository.deleteAll(accounts);
        return true;
    }

}
