package com.bank.customer_service.service;

import com.bank.customer_service.dto.request.CustomerRequest;
import com.bank.customer_service.dto.response.CustomerResponse;
import com.bank.customer_service.exception.CustomerAlreadyExistsException;
import com.bank.customer_service.exception.CustomerNotFoundException;
import com.bank.customer_service.repository.CustomerRepository;
import com.bank.customer_service.service.impl.CustomerServiceImpl;
import com.bank.customer_service.model.Entity.Customer;
import com.bank.customer_service.mapper.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCustomer_ShouldReturnCustomerResponse_WhenCustomerIsCreated() {
        CustomerRequest request = new CustomerRequest();
        // Set request properties

        Customer customer = new Customer(); // Create a Customer entity
        CustomerResponse expectedResponse = new CustomerResponse();
        // Set expected response properties

        when(customerMapper.toEntity(request)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toResponse(customer)).thenReturn(expectedResponse);

        CustomerResponse actualResponse = customerService.createCustomer(request);

        assertEquals(expectedResponse, actualResponse);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void createCustomer_ShouldThrowCustomerAlreadyExistsException_WhenCustomerExists() {
        CustomerRequest request = new CustomerRequest();
        request.setEmail("test@example.com"); // Set the email property

        when(customerMapper.toEntity(request)).thenReturn(new Customer());
        when(customerRepository.existsByEmail(request.getEmail())).thenReturn(true); // Ensure the email is checked

        assertThrows(CustomerAlreadyExistsException.class, () -> customerService.createCustomer(request));
        verify(customerRepository, times(1)).existsByEmail(request.getEmail()); // Verify the correct email is checked
    }

    @Test
    void getAllCustomers_ShouldReturnPageOfCustomers() {
        Pageable pageable = Pageable.unpaged();
        Customer customer = new Customer(); // Create a Customer entity
        CustomerResponse customerResponse = new CustomerResponse(); // Create a CustomerResponse DTO
        Page<Customer> expectedPage = new PageImpl<>(Collections.singletonList(customer));

        when(customerRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<CustomerResponse> actualPage = customerService.getAllCustomers(pageable);

        assertEquals(expectedPage.getContent().size(), actualPage.getContent().size());
        verify(customerRepository, times(1)).findAll(pageable);
    }

    @Test
    void getCustomerById_ShouldReturnCustomerResponse_WhenCustomerExists() {
        Long customerId = 1L;
        CustomerResponse expectedResponse = new CustomerResponse();
        // Set expected response properties

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(new Customer()));
        when(customerMapper.toResponse(any())).thenReturn(expectedResponse);

        CustomerResponse actualResponse = customerService.getCustomerById(customerId);

        assertEquals(expectedResponse, actualResponse);
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    void getCustomerById_ShouldThrowCustomerNotFoundException_WhenCustomerDoesNotExist() {
        Long customerId = 1L;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(customerId));
        verify(customerRepository, times(1)).findById(customerId);
    }
} 