package com.cauanlagrotta.order_platform_challange.controller;

import com.cauanlagrotta.order_platform_challange.dtos.CustomerRequestDTO;
import com.cauanlagrotta.order_platform_challange.entity.Customer;
import com.cauanlagrotta.order_platform_challange.services.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping
  public Customer create(@RequestBody CustomerRequestDTO dto){
    return customerService.create(dto);
  }
}
