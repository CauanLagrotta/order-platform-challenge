package com.cauanlagrotta.order_platform_challange.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", nullable = false, updatable = false)
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @Email
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  public Customer(UUID id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public Customer() {
  }
}
