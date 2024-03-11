package com.hrs.hotel.booking.endpoint.shared.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class is to handle {@link ApiException} and {@link RuntimeException}.
 */
@ControllerAdvice
@Slf4j
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ErrorMessage> handleApiException(ApiException apiException) {
    return ResponseEntity.status(apiException.getStatus()).body(
        ErrorMessage.builder().clientMessage(apiException.getMessage())
            .internalMessage(apiException.getInternalMessage()).build());
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorMessage> handleInternalServerException(RuntimeException runtimeException) {
    log.error(runtimeException.getMessage(), runtimeException);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
        ErrorMessage.builder().clientMessage("").internalMessage(runtimeException.getMessage())
            .build()
    );
  }
}
