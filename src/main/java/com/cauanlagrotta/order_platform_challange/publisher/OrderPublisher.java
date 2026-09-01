package com.cauanlagrotta.order_platform_challange.publisher;

import com.cauanlagrotta.order_platform_challange.config.RabbitMQConfig;
import com.cauanlagrotta.order_platform_challange.dto.OrderMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderPublisher {

  private final RabbitTemplate rabbitTemplate;

  public OrderPublisher(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void publishOrderCreated(OrderMessage message){
    rabbitTemplate.convertAndSend(
        RabbitMQConfig.ORDER_EXCHANGE,
        RabbitMQConfig.ORDER_CREATED_BIND,
        message
    );
  }

  public void publishOrderConfirmed(OrderMessage message){
    rabbitTemplate.convertAndSend(
        RabbitMQConfig.ORDER_EXCHANGE,
        RabbitMQConfig.ORDER_CONFIRMED_BIND,
        message
    );
  }


}
