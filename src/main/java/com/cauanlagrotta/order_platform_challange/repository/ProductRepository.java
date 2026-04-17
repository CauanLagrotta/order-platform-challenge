package com.cauanlagrotta.order_platform_challange.repository;

import com.cauanlagrotta.order_platform_challange.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
