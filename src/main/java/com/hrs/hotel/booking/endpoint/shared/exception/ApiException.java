package com.hrs.hotel.booking.endpoint.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This class is to represent the attributes and define the customized exception to support exeption
 * handler in endpoint.
 */
@Getter
public class ApiException extends RuntimeException {

  private HttpStatus status;
  private String internalMessage;

  public ApiException(HttpStatus httpStatus, String message, String internalMessage,
      Throwable throwable) {
    super(message, throwable);
    this.internalMessage = internalMessage;
    this.status = httpStatus;
  }


}
