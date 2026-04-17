package com.cauanlagrotta.order_platform_challange.dto;

import com.cauanlagrotta.order_platform_challange.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequestDTO(@NotBlank String name,
                                @NotNull BigDecimal price,
                                Long stockQuantity) {

  public Product toProduct(){
    return new Product(
        name,
        price,
        stockQuantity == null ? 0L : stockQuantity
    );
  }
}
