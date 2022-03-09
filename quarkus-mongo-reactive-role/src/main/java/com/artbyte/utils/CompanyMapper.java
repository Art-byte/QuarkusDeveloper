package com.artbyte.utils;

import com.artbyte.dto.CompanyDTO;
import com.artbyte.entity.Company;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface CompanyMapper {
    
    CompanyDTO toResource(Company company);
    Company toModel(CompanyDTO companyDTO);
}
