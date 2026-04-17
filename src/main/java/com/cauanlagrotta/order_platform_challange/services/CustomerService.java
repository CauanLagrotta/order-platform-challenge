package com.cauanlagrotta.order_platform_challange.services;

import com.cauanlagrotta.order_platform_challange.dto.CustomerRequestDTO;
import com.cauanlagrotta.order_platform_challange.entity.Customer;
import com.cauanlagrotta.order_platform_challange.exceptions.EmailAlreadyExistsException;
import com.cauanlagrotta.order_platform_challange.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Customer create(CustomerRequestDTO dto){
    if(customerRepository.existsByEmail(dto.email())){
      throw new EmailAlreadyExistsException();
    }

    return customerRepository.save(dto.toCustomer());
  }
}
