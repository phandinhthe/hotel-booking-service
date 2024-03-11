package com.hrs.hotel.booking.endpoint.booking.api.request;


import com.hrs.hotel.booking.endpoint.booking.entity.BookingStatus;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * This class is to represent all attributes needed for creating/updating booking.
 */
@Data
@Builder
@Jacksonized
public class BookingRequest {

  private String id;
  private String roomId;
  private String customerId;

  private BookingStatus status;
  private ZonedDateTime checkInDate;
  private ZonedDateTime checkOutDate;
}
