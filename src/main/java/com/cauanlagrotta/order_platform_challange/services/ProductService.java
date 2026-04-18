package com.cauanlagrotta.order_platform_challange.services;

import com.cauanlagrotta.order_platform_challange.dto.ProductRequestDTO;
import com.cauanlagrotta.order_platform_challange.dto.ProductResponseDTO;
import com.cauanlagrotta.order_platform_challange.entity.Product;
import com.cauanlagrotta.order_platform_challange.exceptions.ProductNotFoundException;
import com.cauanlagrotta.order_platform_challange.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product create(ProductRequestDTO dto){
    return productRepository.save(dto.toProduct());
  }

  public Page<ProductResponseDTO> findAll(PageRequest pageRequest){
    var products = productRepository.findAll(pageRequest);
    return products.map(ProductResponseDTO::fromEntity);
  }

  public ProductResponseDTO findById(UUID id){
    Optional<Product> product = productRepository.findById(id);
    return ProductResponseDTO.fromEntity(
        product.orElseThrow(ProductNotFoundException::new));
  }
}
