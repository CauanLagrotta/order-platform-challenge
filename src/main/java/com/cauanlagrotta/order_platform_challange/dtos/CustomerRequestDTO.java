package com.cauanlagrotta.order_platform_challange.dtos;

import com.cauanlagrotta.order_platform_challange.entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequestDTO(@NotNull String name,
                                 @NotNull @Email String email) {

  public Customer toCustomer(){
    return new Customer(
        name,
        email
    );
  }
}
