package com.cauanlagrotta.order_platform_challange.services;

import com.cauanlagrotta.order_platform_challange.entity.Product;
import com.cauanlagrotta.order_platform_challange.exceptions.InsufficientStockException;
import com.cauanlagrotta.order_platform_challange.exceptions.ProductNotFoundException;
import com.cauanlagrotta.order_platform_challange.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class StockService {
  private final ProductRepository productRepository;

  public StockService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Transactional
  public void reserve(UUID productId, int quantity){
    Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

    if(product.getStockQuantity() < quantity){
      throw new InsufficientStockException();
    }

    product.setStockQuantity(product.getStockQuantity() - quantity);
    productRepository.save(product);
  }

  @Transactional
  public void rollback(UUID productId, int quantity){
    Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

    product.setStockQuantity(product.getStockQuantity() + quantity);
    productRepository.save(product);
  }
}
