package com.cauanlagrotta.order_platform_challange.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
  @ExceptionHandler(GlobalExceptionHandler.class)
  public ProblemDetail handleGlobalException(GlobalExceptionHandler e){
    return e.toProblemDetail(e.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
    var fieldErrors = e.getFieldErrors()
        .stream()
        .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
        .toList();

    var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    pb.setTitle("Invalid request parameters.");
    pb.setProperty("invalid-params", fieldErrors);

    return pb;
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
    var pb = ProblemDetail.forStatus(422);
    pb.setTitle("Invalid order status.");
    pb.setDetail("All the available status are: 'PENDING', 'PROCESSING', 'CONFIRMED', 'SHIPPED' and 'CANCELLED'");
    return pb;
  }

  private record InvalidParam(String name, String reason) { }
}
