package com.cauanlagrotta.order_platform_challange.repository;

import com.cauanlagrotta.order_platform_challange.entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
  Optional<Customer> findByEmail(@NotNull @Email String email);
}
