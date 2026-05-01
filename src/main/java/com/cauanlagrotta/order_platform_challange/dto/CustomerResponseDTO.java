package com.cauanlagrotta.order_platform_challange.dto;

import com.cauanlagrotta.order_platform_challange.entity.Customer;

import java.util.UUID;

public record CustomerResponseDTO(UUID id, String name, String email) {

  public static CustomerResponseDTO fromEntity(Customer customer) {
    return new CustomerResponseDTO(customer.getId(), customer.getName(), customer.getEmail());
  }
}
