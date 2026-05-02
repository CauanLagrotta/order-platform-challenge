package com.cauanlagrotta.order_platform_challange.repository;

import com.cauanlagrotta.order_platform_challange.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
  @Query("SELECT o FROM Order o WHERE o.customerId.id = :customerId")
  Page<Order> findAllOrdersByCustomerId(PageRequest pageRequest, UUID customerId);
}
