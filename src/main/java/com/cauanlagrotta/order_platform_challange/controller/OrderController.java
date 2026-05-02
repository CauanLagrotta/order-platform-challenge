package com.cauanlagrotta.order_platform_challange.controller;

import com.cauanlagrotta.order_platform_challange.dto.ListResponse;
import com.cauanlagrotta.order_platform_challange.dto.OrderRequestDTO;
import com.cauanlagrotta.order_platform_challange.dto.OrderResponseDTO;
import com.cauanlagrotta.order_platform_challange.dto.PaginationResponse;
import com.cauanlagrotta.order_platform_challange.services.OrderService;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public ResponseEntity<OrderResponseDTO> create(@RequestBody OrderRequestDTO dto) {
    OrderResponseDTO response = orderService.create(dto);
    return ResponseEntity.status(201).body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderResponseDTO> findById(@PathVariable UUID id){
    return ResponseEntity.ok(orderService.findById(id));
  }

  @GetMapping
  public ResponseEntity<ListResponse<OrderResponseDTO>>
      listCustomersOrders(@RequestParam("customerId") UUID customerId,
                          @RequestParam(name = "page", defaultValue = "0") Integer page,
                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){

    var pageResponse = orderService.findAllByCustomerId(PageRequest.of(page, pageSize), customerId);

    return ResponseEntity.ok(new ListResponse<>(
        Map.of("totalOrders", pageResponse.getTotalElements()),
        pageResponse.getContent(),
        PaginationResponse.fromPage(pageResponse)
    ));
  }
}
