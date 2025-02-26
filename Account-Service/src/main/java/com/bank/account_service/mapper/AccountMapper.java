package com.bank.account_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bank.account_service.dto.request.AccountRequest;
import com.bank.account_service.dto.response.AccountResponse;
import com.bank.account_service.model.Entity.Account;

/*
 * Mapper pour le compte
 */ 
@Mapper(componentModel = "spring")
public interface AccountMapper {
    /*
     * Convertit une entité en DTO
     * @param account
     * @return AccountResponse
     */
    AccountResponse toResponse(Account account);
    /*
     * Convertit un DTO en entité
     * @param request
     * ignore l'id
     * @return Account
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", source = "type")
    Account toEntity(AccountRequest request);

  

}
