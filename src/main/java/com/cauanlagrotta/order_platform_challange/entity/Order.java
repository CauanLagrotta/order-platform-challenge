package com.cauanlagrotta.order_platform_challange.entity;

import com.cauanlagrotta.order_platform_challange.entity.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @Column(name = "status")
  private OrderStatus status = OrderStatus.PENDING;

  @ManyToOne
  @JoinColumn(name = "product")
  private Product product;

  @Column(name = "total")
  private BigDecimal total;

  @Column(name = "quantity")
  private int quantity;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public Order(@NotNull UUID customer, OrderStatus pending, @NotNull UUID product, @NotNull int quantity) {
  }

  public Order(UUID id, Customer customer, OrderStatus status, Product product, BigDecimal total, int quantity, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.customer = customer;
    this.status = status;
    this.product = product;
    this.total = total;
    this.quantity = quantity;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Order(Customer customer, OrderStatus status, Product product, int quantity) {
    this.customer = customer;
    this.status = status;
    this.product = product;
    this.quantity = quantity;
  }

  public Order() {
  }
}
