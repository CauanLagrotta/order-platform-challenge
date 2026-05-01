package com.cauanlagrotta.order_platform_challange.entity;

import com.cauanlagrotta.order_platform_challange.entity.enums.OrderStatus;
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
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customerId;

  @Column(name = "status")
  private OrderStatus status = OrderStatus.PENDING;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product productId;

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

  public Order(@NotNull UUID customerId, OrderStatus pending, @NotNull UUID productId, @NotNull int quantity) {
  }

  public Order(UUID id, Customer customerId, OrderStatus status, Product productId, BigDecimal total, int quantity, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.customerId = customerId;
    this.status = status;
    this.productId = productId;
    this.total = total;
    this.quantity = quantity;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Order(Customer customerId, OrderStatus status, Product productId, int quantity) {
    this.customerId = customerId;
    this.status = status;
    this.productId = productId;
    this.quantity = quantity;
  }
}
