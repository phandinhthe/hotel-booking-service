package com.hrs.hotel.booking.endpoint.api;

import com.hrs.hotel.booking.endpoint.hotel.api.response.HotelResponse;
import com.hrs.hotel.booking.endpoint.hotel.service.HotelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is to represent all features of Hotel Endpoint.
 */
@RestController
@RequestMapping("api/v1/hotels")
public class HotelController {

  private final HotelService service;

  public HotelController(final HotelService service) {
    this.service = service;
  }

  /**
   * This method is to find the hotel at the specific location.
   *
   * @param country  country of the hotel.
   * @param city     city of the hotel
   * @param pageable this param is to support pagination.
   * @return list of {@link HotelResponse}
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<HotelResponse> findByCountryAndCity(@RequestParam(required = false) String country,
      @RequestParam(required = false) String city, @PageableDefault(page = 0, size = 50) Pageable pageable) {
    return service.findByCountryAndCity(country, city, pageable);
  }
}
