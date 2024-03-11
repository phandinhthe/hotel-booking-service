package com.hrs.hotel.booking.endpoint.booking.mapper;

import com.hrs.hotel.booking.endpoint.booking.api.request.BookingRequest;
import com.hrs.hotel.booking.endpoint.booking.api.response.BookingResponse;
import com.hrs.hotel.booking.endpoint.booking.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * This class is to represent all methods for Booking Entity mapping.
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookingMapper {

  /**
   * This method is to map {@link Booking} to {@link BookingResponse}.
   *
   * @param booking {@link Booking}
   * @return booking response {@link BookingResponse}
   */
  @Mapping(source = "room.hotel", target = "hotel")
  BookingResponse toResponse(Booking booking);

  /**
   * This method is to create and map from {@link BookingRequest} to {@link Booking};
   * @param request
   * @return
   */
  Booking fromRequest(BookingRequest request);

  /**
   * This method is to map values  between two entities {@link Booking}.
   *
   * @param sourceBooking source {@link Booking}
   * @param desBooking    destination {@link Booking}
   */
  void mapValues(Booking sourceBooking, @MappingTarget Booking desBooking);

  /**
   * This method is to map values from {@link BookingRequest} to {@link Booking}.
   *
   * @param request {@link BookingRequest}
   * @param booking {@link Booking}
   */
  void mapValues(BookingRequest request, @MappingTarget Booking booking);
}
