package com.utils;

import com.entity.Customer;
import com.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface CustomerMapper {

    Customer toEntity(CustomerDTO domain);
    CustomerDTO toDomain(Customer entity);
}
