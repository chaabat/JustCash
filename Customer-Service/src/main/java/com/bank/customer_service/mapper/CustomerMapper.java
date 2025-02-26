package com.bank.customer_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bank.customer_service.dto.request.CustomerRequest;
import com.bank.customer_service.dto.response.CustomerResponse;
import com.bank.customer_service.model.Entity.Customer;

/*
 * Mapper pour le client
 */ 
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    /*
     * Convertit une entité en DTO
     * @param customer
     * @return CustomerResponse
     */
    CustomerResponse toResponse(Customer customer);
    /*
     * Convertit un DTO en entité
     * @param request
     * ignore l'id
     * @return Customer
     */
    @Mapping(target = "id", ignore = true)
    Customer toEntity(CustomerRequest request);

}
