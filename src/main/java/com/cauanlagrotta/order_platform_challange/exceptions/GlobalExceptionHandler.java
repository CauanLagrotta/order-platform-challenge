package com.cauanlagrotta.order_platform_challange.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class GlobalExceptionHandler extends RuntimeException {
  public ProblemDetail toProblemDetail(String message){
    var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    pb.setTitle("Order Platform internal server error.");
    pb.setDetail(String.format("Unexpected error ocurred: %s", message));
    return pb;
  }
}
