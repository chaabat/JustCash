package com.bank.account_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.account_service.dto.request.AccountRequest;
import com.bank.account_service.dto.response.AccountResponse;

/*
 * Service pour le compte
 */
@Service
public interface AccountService {
    /*
     * Crée un compte
     * @param request
     * @return AccountResponse
     */
    AccountResponse createAccount(AccountRequest request);
    /*
     * Récupère tous les comptes
     * @return List<AccountResponse>
     */
    List<AccountResponse> getAllAccounts();
    /*
     * Récupère un compte par son id
     * @param id
     * @return AccountResponse
     */
    AccountResponse getAccountById(Long id);
    /*
     * Récupère tous les comptes d'un client
     * @param clientId
     * @return List<AccountResponse>
     */
    List<AccountResponse> getAccountsByClientId(Long clientId);
    /*
     * Supprime un compte
     * @param id
     * @return boolean
     */
    boolean deleteAccount(Long id);

    /*
     * Supprime tous les comptes d'un client
     * @param clientId
     * @return boolean
     */
    boolean deleteAccountByClientId(Long clientId);

}   