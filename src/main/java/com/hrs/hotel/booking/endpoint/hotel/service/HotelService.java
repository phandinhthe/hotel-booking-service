package com.hrs.hotel.booking.endpoint.hotel.service;

import com.hrs.hotel.booking.endpoint.hotel.api.response.HotelResponse;
import com.hrs.hotel.booking.endpoint.hotel.entity.Hotel;
import com.hrs.hotel.booking.endpoint.hotel.mapper.HotelMapper;
import com.hrs.hotel.booking.endpoint.hotel.repository.HotelRepository;
import com.hrs.hotel.booking.endpoint.shared.exception.ApiException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * This class is to represent all Hotel 's business methods.
 */
@Service
@Slf4j
public class HotelService {

  private final HotelRepository repository;
  private final HotelMapper hotelMapper;

  public HotelService(final HotelRepository repository, final HotelMapper hotelMapper) {
    this.repository = repository;
    this.hotelMapper = hotelMapper;
  }

  /**
   * This class is to find all hotel matched conditions: country and city requests.
   *
   * @param country country
   * @param city city
   * @param pageable support pagination
   * @return list of {@link HotelResponse}
   */
  public List<HotelResponse> findByCountryAndCity(String country, String city, Pageable pageable) {
    List<Hotel> hotels = repository.findByLocationCountryAndCity(country, city,
        pageable.getPageSize(), pageable.getOffset());

    if (CollectionUtils.isEmpty(hotels)) {
      log.error(String.format("There are no hotels found by country %s and city %s", country, city));
      throw new ApiException(HttpStatus.BAD_REQUEST,
          String.format("There are no hotels found by country %s and city %s", country, city),
          String.format("There are no hotels found by country %s and city %s", country, city),
          null);
    }
    return hotelMapper.toResponses(hotels);
  }
}
