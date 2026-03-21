package com.cauanlagrotta.order_platform_challange.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.util.Optional;

public class EmailAlreadyExistsException extends GlobalExceptionHandler {
  @Override
  public ProblemDetail toProblemDetail(String message){
    var pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);
    pb.setTitle("Email already exists.");
    pb.setDetail("The email address you provided is already in use.");
    return pb;
  }
}
