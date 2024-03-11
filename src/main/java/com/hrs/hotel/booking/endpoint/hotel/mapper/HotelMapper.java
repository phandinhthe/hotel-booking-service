package com.hrs.hotel.booking.endpoint.hotel.mapper;

import com.hrs.hotel.booking.endpoint.hotel.api.response.HotelResponse;
import com.hrs.hotel.booking.endpoint.hotel.entity.Hotel;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * This class is to represent all methods for mapping {@link Hotel}.
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HotelMapper {

  HotelResponse toResponse(Hotel hotel);

  List<HotelResponse> toResponses(List<Hotel> hotels);
}
