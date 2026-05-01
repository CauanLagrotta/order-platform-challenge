package com.cauanlagrotta.order_platform_challange.dto;

import com.cauanlagrotta.order_platform_challange.entity.Order;
import com.cauanlagrotta.order_platform_challange.entity.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderRequestDTO(@NotNull UUID customerId,
                              @NotNull UUID productId,
                              @NotNull int quantity) {

  public Order toEntity() {
    return new Order(customerId, OrderStatus.PENDING, productId, quantity);
  }
}
