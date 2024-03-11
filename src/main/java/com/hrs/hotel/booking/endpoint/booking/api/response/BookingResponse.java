package com.hrs.hotel.booking.endpoint.booking.api.response;


import com.hrs.hotel.booking.endpoint.booking.entity.BookingStatus;
import com.hrs.hotel.booking.endpoint.hotel.entity.Hotel;
import com.hrs.hotel.booking.endpoint.shared.entity.Customer;
import com.hrs.hotel.booking.endpoint.shared.entity.Room;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is to represent all attributes need to return after creating/updating booking.
 */
@Getter
@Setter
public class BookingResponse {

  private String id;
  private BookingStatus status;
  private ZonedDateTime checkInDate;
  private ZonedDateTime checkOutDate;

  private Hotel hotel;
  private Room room;
  private Customer customer;
}
