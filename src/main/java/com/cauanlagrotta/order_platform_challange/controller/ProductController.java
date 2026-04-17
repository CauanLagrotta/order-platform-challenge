package com.cauanlagrotta.order_platform_challange.controller;

import com.cauanlagrotta.order_platform_challange.dto.ProductRequestDTO;
import com.cauanlagrotta.order_platform_challange.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<Void> create(@Valid @RequestBody ProductRequestDTO dto){
    productService.create(dto);
    return ResponseEntity.noContent().build();
  }
}
