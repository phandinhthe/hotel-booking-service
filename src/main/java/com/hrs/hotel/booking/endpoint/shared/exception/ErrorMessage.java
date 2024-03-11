package com.hrs.hotel.booking.endpoint.shared.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

/**
 * This class is to represent the attributes of an error message returned to client.
 */
@Setter
@Getter
@Builder
@Jacksonized
public class ErrorMessage {

  private String clientMessage;
  private String internalMessage;

}
