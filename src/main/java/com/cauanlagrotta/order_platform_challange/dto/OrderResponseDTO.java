package com.cauanlagrotta.order_platform_challange.dto;

import com.cauanlagrotta.order_platform_challange.entity.Customer;
import com.cauanlagrotta.order_platform_challange.entity.Order;
import com.cauanlagrotta.order_platform_challange.entity.Product;
import com.cauanlagrotta.order_platform_challange.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderResponseDTO(UUID id,
                               Customer customerId,
                               Product productId,
                               int quantity,
                               OrderStatus status,
                               BigDecimal total) {

  public static OrderResponseDTO fromEntity(Order order) {
    return new OrderResponseDTO(order.getId(), order.getCustomerId(), order.getProductId(),
        order.getQuantity(), order.getStatus(), order.getTotal());
  }
}
