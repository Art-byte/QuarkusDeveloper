package com.service;

import com.dto.CustomerDTO;
import com.entity.Customer;
import com.exception.ServiceException;
import com.repository.CustomerRepository;
import com.utils.CustomerMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService( CustomerRepository customerRepository, CustomerMapper customerMapper){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    public List<CustomerDTO> findAll(){
        return customerRepository.findAll().stream()
                .map(customerMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<CustomerDTO> findById(Integer id){
        return customerRepository.findByIdOptional(id).map(customerMapper::toDomain);
    }

    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO){
        Customer customer = customerMapper.toEntity(customerDTO);
        customerRepository.persist(customer);
        return customerMapper.toDomain(customer);
    }

    @Transactional
    public CustomerDTO update(CustomerDTO customerDTO){
        if(customerDTO.getCustomerId() == null){
            throw  new ServiceException("Customer does not have a customerId");
        }
        Optional<Customer> optional = customerRepository.findByIdOptional(customerDTO.getCustomerId());
        if(optional.isEmpty()){
            throw new ServiceException(String.format("No customer found for customerId[%s]", customerDTO.getCustomerId()));
        }

        Customer entity = optional.get();
        entity.setFirstName(customerDTO.getFirstName());
        entity.setMiddleName(customerDTO.getMiddleName());
        entity.setLastName(customerDTO.getLastName());
        entity.setSuffix(customerDTO.getSuffix());
        entity.setEmail(customerDTO.getEmail());
        entity.setPhone(customerDTO.getPhone());
        return customerMapper.toDomain(entity);
    }





}
















