package com.cauanlagrotta.order_platform_challange.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class OrderNotFoundException extends GlobalExceptionHandler {
  @Override
  public ProblemDetail toProblemDetail(String message){
    var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    pb.setTitle("Order not found.");
    pb.setDetail("The searched order was not found.");
    return pb;
  }
}
