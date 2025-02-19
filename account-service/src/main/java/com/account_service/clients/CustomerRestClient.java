package com.account_service.clients;

import com.account_service.dtos.CustomerDTO;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerRestClient {
    private final RestTemplate restTemplate;
    private final String CUSTOMER_SERVICE_URL = "http://localhost:8081/api/customers/";

    public CustomerRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CustomerDTO getCustomerById(Long id) {
        return restTemplate.getForObject(CUSTOMER_SERVICE_URL + id, CustomerDTO.class);
    }
} 