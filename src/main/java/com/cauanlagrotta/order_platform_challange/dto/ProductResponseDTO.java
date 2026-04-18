package com.cauanlagrotta.order_platform_challange.dto;

import com.cauanlagrotta.order_platform_challange.entity.Product;
import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(UUID id,
                                 String name,
                                 BigDecimal price,
                                 Long stockQuantity) {

  public static ProductResponseDTO fromEntity(Product product){
    return new ProductResponseDTO(product.getId(), product.getName(), product.getPrice(), product.getStockQuantity());
  }
}
