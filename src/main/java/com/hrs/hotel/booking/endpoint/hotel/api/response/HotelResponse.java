package com.hrs.hotel.booking.endpoint.hotel.api.response;

import com.hrs.hotel.booking.endpoint.shared.entity.Location;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * This class is to represent all attributes of Hotel Response.
 */
@Data
@Builder
public class HotelResponse {
  private String id;
  private String name;
  private Location location;
  private String phoneNumber;

  private ZonedDateTime createdDate;
  private ZonedDateTime lastUpdatedDate;

}
