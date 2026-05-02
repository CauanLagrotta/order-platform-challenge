package com.cauanlagrotta.order_platform_challange.controller;

import com.cauanlagrotta.order_platform_challange.dto.OrderRequestDTO;
import com.cauanlagrotta.order_platform_challange.dto.OrderResponseDTO;
import com.cauanlagrotta.order_platform_challange.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public ResponseEntity<OrderResponseDTO> create(@RequestBody OrderRequestDTO dto) {
    orderService.create(dto);
    return ResponseEntity.status(201).body(orderService.create(dto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderResponseDTO> findById(@PathVariable UUID id){
    return ResponseEntity.ok(orderService.findById(id));
  }
}
