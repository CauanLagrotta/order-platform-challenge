package com.cauanlagrotta.order_platform_challange.services;

import com.cauanlagrotta.order_platform_challange.dto.ProductRequestDTO;
import com.cauanlagrotta.order_platform_challange.entity.Product;
import com.cauanlagrotta.order_platform_challange.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product create(ProductRequestDTO dto){
    return productRepository.save(dto.toProduct());
  }
}
