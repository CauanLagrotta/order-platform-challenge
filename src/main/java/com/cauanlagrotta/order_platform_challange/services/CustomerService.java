package com.cauanlagrotta.order_platform_challange.services;

import com.cauanlagrotta.order_platform_challange.dtos.CustomerRequestDTO;
import com.cauanlagrotta.order_platform_challange.entity.Customer;
import com.cauanlagrotta.order_platform_challange.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Customer create(CustomerRequestDTO dto){
    var emailAlreadyExists = customerRepository.findByEmail(dto.email());

    if(emailAlreadyExists.isPresent()){
      throw new RuntimeException("Email already exists");
    }

    return customerRepository.save(dto.toCustomer());
  }
}
