package com.cauanlagrotta.order_platform_challange.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", nullable = false, updatable = false)
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  @Column(name = "stock_quantity", nullable = false)
  private Long stockQuantity = 0L;

  @Version
  @Column(name = "version", nullable = false)
  private Integer version;

  public Product(String name, BigDecimal price, Long stockQuantity, Integer version) {
    this.name = name;
    this.price = price;
    this.stockQuantity = stockQuantity;
    this.version = version;
  }

  public Product() {
  }

  public Product(String name, BigDecimal price, Long stockQuantity) {
    this.name = name;
    this.price = price;
    this.stockQuantity = stockQuantity;
  }
}
