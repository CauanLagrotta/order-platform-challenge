package com.cauanlagrotta.order_platform_challange.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ProductNotFoundException extends GlobalExceptionHandler {
  @Override
  public ProblemDetail toProblemDetail(String message){
    var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    pb.setTitle("Product not found.");
    pb.setDetail("The searched user doesn't exists.");
    return pb;
  }
}
