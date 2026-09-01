package com.cauanlagrotta.order_platform_challange.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

  public static final String ORDER_EXCHANGE = "order.exchange";

  public static final String ORDER_CREATED_QUEUE = "order.created.queue";
  public static final String ORDER_CONFIRMED_QUEUE = "order.confirmed.queue";
  public static final String ORDER_FAILED_QUEUE = "order.failed.queue";

  public static final String ORDER_CREATED_BIND = "order.created.bind";
  public static final String ORDER_CONFIRMED_BIND = "order.confirmed.bind";
  public static final String ORDER_FAILED_BIND = "order.failed.bind";

  @Bean
  public TopicExchange orderExchange(){
    return new TopicExchange(ORDER_EXCHANGE);
  }

  @Bean
  public Queue orderCreatedQueue(){
    return new Queue(ORDER_CREATED_QUEUE, true, false, false, deadLetterArgs());
  }

  @Bean
  public Queue orderConfirmedQueue(){
    return new Queue(ORDER_CONFIRMED_QUEUE, true, false, false, deadLetterArgs());
  }

  @Bean
  public Queue orderFailedQueue(){
    return new Queue(ORDER_FAILED_QUEUE, true, false, false, null);
  }

  @Bean
  public Binding bindOrdersCreated(){
    return BindingBuilder
        .bind(orderCreatedQueue())
        .to(orderExchange())
        .with(ORDER_CREATED_BIND);
  }

  @Bean
  public Binding bindOrdersConfirmed(){
    return BindingBuilder
        .bind(orderConfirmedQueue())
        .to(orderExchange())
        .with(ORDER_CONFIRMED_BIND);
  }

  @Bean
  public Binding bindOrdersFailed(){
    return BindingBuilder
        .bind(orderFailedQueue())
        .to(orderExchange())
        .with(ORDER_FAILED_BIND);
  }

  @Bean
  public JacksonJsonMessageConverter jacksonToJsonMessageConverter(){
    return new JacksonJsonMessageConverter();
  }

  private Map<String, Object> deadLetterArgs() {
    Map<String, Object> args = new HashMap<>();
    args.put("x-dead-letter-exchange", ORDER_EXCHANGE);
    args.put("x-dead-letter-routing-key", ORDER_FAILED_BIND);
    args.put("x-message-ttl", 30000);
    return args;
  }
}
