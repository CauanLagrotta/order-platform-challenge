package com.cauanlagrotta.order_platform_challange.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order orderId;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product productId;

  @Column(name = "quantity")
  private Long quantity;

  @Column(name = "unit_price")
  private Double unitPrice;

  public OrderItem() {
  }

  public OrderItem(UUID id, Order orderId, Product productId, Long quantity, Double unitPrice) {
    this.id = id;
    this.orderId = orderId;
    this.productId = productId;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
  }
}
