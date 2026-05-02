package com.cauanlagrotta.order_platform_challange.services;

import com.cauanlagrotta.order_platform_challange.dto.OrderRequestDTO;
import com.cauanlagrotta.order_platform_challange.dto.OrderResponseDTO;
import com.cauanlagrotta.order_platform_challange.entity.Customer;
import com.cauanlagrotta.order_platform_challange.entity.Order;
import com.cauanlagrotta.order_platform_challange.entity.Product;
import com.cauanlagrotta.order_platform_challange.entity.enums.OrderStatus;
import com.cauanlagrotta.order_platform_challange.exceptions.CustomerNotFoundException;
import com.cauanlagrotta.order_platform_challange.exceptions.InsufficientStockException;
import com.cauanlagrotta.order_platform_challange.exceptions.OrderNotFoundException;
import com.cauanlagrotta.order_platform_challange.exceptions.ProductNotFoundException;
import com.cauanlagrotta.order_platform_challange.repository.CustomerRepository;
import com.cauanlagrotta.order_platform_challange.repository.OrderRepository;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import com.cauanlagrotta.order_platform_challange.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final CustomerRepository customerRepository;

  public OrderService(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.customerRepository = customerRepository;
  }

  @Transactional
  public OrderResponseDTO create(OrderRequestDTO dto) {
    Product product = productRepository.findById(dto.productId())
        .orElseThrow(ProductNotFoundException::new);

    Customer customer = customerRepository.findById(dto.customerId())
        .orElseThrow(CustomerNotFoundException::new);

    BigDecimal total = BigDecimal.valueOf(dto.quantity()).multiply(product.getPrice());

    Order order = new Order(customer, OrderStatus.PENDING, product, dto.quantity());
    order.setTotal(total);

    if(product.getStockQuantity() < order.getQuantity()){
      throw new InsufficientStockException();
    }else{
      product.setStockQuantity(product.getStockQuantity() - dto.quantity());
    }

    orderRepository.save(order);

    return new OrderResponseDTO(order.getId(), order.getCustomerId(), order.getProductId(), order.getQuantity(), order.getStatus(), total);
  }

  public OrderResponseDTO findById(UUID id){
    Optional<Order> order = orderRepository.findById(id);
    return OrderResponseDTO.fromEntity(order.orElseThrow(OrderNotFoundException::new));
  }
}
