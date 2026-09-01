package com.cauanlagrotta.order_platform_challange.consumer;

import com.cauanlagrotta.order_platform_challange.config.RabbitMQConfig;
import com.cauanlagrotta.order_platform_challange.dto.OrderMessage;
import com.cauanlagrotta.order_platform_challange.entity.Order;
import com.cauanlagrotta.order_platform_challange.entity.enums.OrderStatus;
import com.cauanlagrotta.order_platform_challange.exceptions.ProductNotFoundException;
import com.cauanlagrotta.order_platform_challange.publisher.OrderPublisher;
import com.cauanlagrotta.order_platform_challange.repository.OrderRepository;
import com.cauanlagrotta.order_platform_challange.services.StockService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedConsumer {

  private final StockService stockService;
  private final OrderRepository orderRepository;
  private final OrderPublisher orderPublisher;

  public OrderCreatedConsumer(StockService stockService, OrderRepository orderRepository, OrderPublisher orderPublisher) {
    this.stockService = stockService;
    this.orderRepository = orderRepository;
    this.orderPublisher = orderPublisher;
  }

  @RabbitListener(queues = RabbitMQConfig.ORDER_CREATED_QUEUE)
  public void handleOrderCreated(OrderMessage message){
    Order order = orderRepository.findById(message.orderId()).orElseThrow(ProductNotFoundException::new);

    if(order.getStatus() != OrderStatus.PENDING){
      return;
    }

    try{
      stockService.reserve(message.productId(), message.quantity());
      order.setStatus(OrderStatus.CONFIRMED);
      orderRepository.save(order);
      orderPublisher.publishOrderConfirmed(message);
    }catch (Exception e){
      order.setStatus(OrderStatus.CANCELLED);
      orderRepository.save(order);
    }
  }
}
