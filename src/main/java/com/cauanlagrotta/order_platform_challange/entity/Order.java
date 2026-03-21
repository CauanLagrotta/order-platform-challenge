package com.cauanlagrotta.order_platform_challange.entity;

import com.cauanlagrotta.order_platform_challange.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

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

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public Order() {
  }

  public Order(UUID id, Customer customerId, OrderStatus status, LocalDateTime createdAt) {
    this.id = id;
    this.customerId = customerId;
    this.status = status;
    this.createdAt = createdAt;
  }
}
