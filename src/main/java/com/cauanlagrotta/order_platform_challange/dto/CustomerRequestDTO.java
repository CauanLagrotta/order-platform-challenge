package com.cauanlagrotta.order_platform_challange.dto;

import com.cauanlagrotta.order_platform_challange.entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDTO(@NotBlank String name,
                                 @NotBlank @Email String email) {

  public Customer toCustomer(){
    return new Customer(
        name,
        email
    );
  }
}
