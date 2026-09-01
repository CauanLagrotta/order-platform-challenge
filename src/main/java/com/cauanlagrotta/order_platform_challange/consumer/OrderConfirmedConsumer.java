package com.cauanlagrotta.order_platform_challange.consumer;

import com.cauanlagrotta.order_platform_challange.config.RabbitMQConfig;
import com.cauanlagrotta.order_platform_challange.dto.OrderMessage;
import com.cauanlagrotta.order_platform_challange.entity.Order;
import com.cauanlagrotta.order_platform_challange.entity.enums.OrderStatus;
import com.cauanlagrotta.order_platform_challange.exceptions.OrderNotFoundException;
import com.cauanlagrotta.order_platform_challange.repository.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConfirmedConsumer {

  private final OrderRepository orderRepository;

  public OrderConfirmedConsumer(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @RabbitListener(queues = RabbitMQConfig.ORDER_CONFIRMED_QUEUE)
  public void handleOrderConfirmed(OrderMessage message){
    Order order = orderRepository.findById(message.orderId()).orElseThrow(OrderNotFoundException::new);

    if(order.getStatus() != OrderStatus.CONFIRMED){
      return;
    }

    try{
      Thread.sleep(5000);
      order.setStatus(OrderStatus.SHIPPED);
      orderRepository.save(order);
    }catch (InterruptedException e){
      Thread.currentThread().interrupt();
    }
  }
}
