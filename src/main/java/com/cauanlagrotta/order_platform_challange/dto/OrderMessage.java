package com.cauanlagrotta.order_platform_challange.dto;

import com.cauanlagrotta.order_platform_challange.entity.Order;

import java.util.UUID;

public record OrderMessage(UUID orderId,
                           UUID customerId,
                           UUID productId,
                           Integer quantity) {

  public static OrderMessage fromOrder(Order order){
    return new OrderMessage(order.getId(),
                            order.getCustomer().getId(),
                            order.getProduct().getId(),
                            order.getQuantity());
  }
}
