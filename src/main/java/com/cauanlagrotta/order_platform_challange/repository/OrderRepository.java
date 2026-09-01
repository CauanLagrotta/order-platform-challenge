package com.cauanlagrotta.order_platform_challange.repository;

import com.cauanlagrotta.order_platform_challange.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
  @EntityGraph(attributePaths = {"product", "customer"})
  Page<Order> findByCustomerId_Id(UUID customerId, PageRequest pageRequest);
}
