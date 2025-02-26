package com.bank.customer_service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bank.customer_service.dto.request.CustomerRequest;
import com.bank.customer_service.dto.response.CustomerResponse;

@Service

/*
 * Interface pour le service de gestion des clients
 */
public interface CustomerService {
    /*
     * Crée un nouveau client
     * @param request
     * @return CustomerResponse
     */
    CustomerResponse createCustomer(CustomerRequest request);
    /*
     * Récupère tous les clients avec pagination
     * @param pageable
     * @return Page<CustomerResponse>
     */
    Page<CustomerResponse> getAllCustomers(Pageable pageable);
    /*
     * Récupère un client par son ID
     * @param id    
     * @return CustomerResponse
     */
    CustomerResponse getCustomerById(Long id);
    /*
     * Supprime un client par son ID
     * @param id
     */
    void deleteCustomer(Long id);

}
