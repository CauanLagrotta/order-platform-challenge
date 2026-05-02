package com.cauanlagrotta.order_platform_challange.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InsufficientStockException extends GlobalExceptionHandler {
  @Override
  public ProblemDetail toProblemDetail(String message){
    var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_CONTENT);
    pb.setTitle("Insufficient stock");
    pb.setDetail("The quantity provided is greater than the available stock.");
    return pb;
  }
}
