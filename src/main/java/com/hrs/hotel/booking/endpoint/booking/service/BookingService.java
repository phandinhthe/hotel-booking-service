package com.hrs.hotel.booking.endpoint.booking.service;

import com.hrs.hotel.booking.endpoint.booking.api.request.BookingRequest;
import com.hrs.hotel.booking.endpoint.booking.api.response.BookingResponse;
import com.hrs.hotel.booking.endpoint.booking.entity.Booking;
import com.hrs.hotel.booking.endpoint.booking.entity.BookingStatus;
import com.hrs.hotel.booking.endpoint.booking.mapper.BookingMapper;
import com.hrs.hotel.booking.endpoint.booking.repository.BookingRepository;
import com.hrs.hotel.booking.endpoint.hotel.entity.Hotel;
import com.hrs.hotel.booking.endpoint.hotel.repository.HotelRepository;
import com.hrs.hotel.booking.endpoint.shared.entity.Customer;
import com.hrs.hotel.booking.endpoint.shared.entity.Room;
import com.hrs.hotel.booking.endpoint.shared.exception.ApiException;
import com.hrs.hotel.booking.endpoint.shared.repository.CustomerRepository;
import com.hrs.hotel.booking.endpoint.shared.repository.RoomRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * This class is for {@link Booking} business activities..
 */
@Service
@Slf4j
public class BookingService {

  @Autowired
  BookingRepository repository;
  @Autowired
  HotelRepository hotelRepository;
  @Autowired
  CustomerRepository customerRepository;
  @Autowired
  RoomRepository roomRepository;

  @Autowired
  BookingMapper bookingMapper;


  /**
   * This method is to create a new {@link Booking}.
   *
   * @param request {@link BookingRequest}
   * @return {@link BookingResponse}
   */
  public BookingResponse create(BookingRequest request) {
    validate(request);
    Booking booking = bookingMapper.fromRequest(request);
    booking.setStatus(BookingStatus.PROCESSING);

    Room room = roomRepository.findById(request.getRoomId())
        .orElseThrow(() -> {
              log.error(
                  String.format("There are no hotel found by room id %s", request.getRoomId()));
              return new ApiException(HttpStatus.BAD_REQUEST,
                  String.format("There are no hotel found by room id %s", request.getRoomId()),
                  String.format("There are no hotel found by room id %s", request.getRoomId()),
                  null);
            }
        );

    Hotel hotel = Optional.of(room.getHotel())
        .orElseThrow(() -> {
              log.error(
                  String.format("There are no hotel found by hotel id %s", room.getHotel().getId()));
              return new ApiException(HttpStatus.BAD_REQUEST,
                  String.format("There are no hotel found by hotel id %s", room.getHotel().getId()),
                  String.format("There are no hotel found by hotel id %s", room.getHotel().getId()),
                  null);
            }
        );

    Customer customer = customerRepository.findById(request.getCustomerId())
        .orElseThrow(() -> {
              log.error(
                  String.format("There are no hotel found by customer id %s", request.getCustomerId()));
              return new ApiException(HttpStatus.BAD_REQUEST,
                  String.format("There are no hotel found by customer id %s", request.getCustomerId()),
                  String.format("There are no hotel found by customer id %s", request.getCustomerId()),
                  null);
            }
        );

    booking.setCustomer(customer);
    booking.setRoom(room);
    booking = repository.save(booking);
    return bookingMapper.toResponse(booking);
  }

  /**
   * This method is to get detail of a specific {@link Booking}.
   *
   * @param id {@link Booking}'s id.
   * @return {@link BookingResponse}
   */
  public BookingResponse detail(String id) {
    Booking booking = repository.findById(id).orElse(Booking.EMPTY);
    if (Booking.EMPTY == booking) {
      log.error(String.format("There are no booking found by id %s", id));
      throw new ApiException(HttpStatus.BAD_REQUEST,
          String.format("There are no booking found by id %s", id),
          String.format("There are no booking found by id %s", id),
          null);
    }
    return bookingMapper.toResponse(booking);
  }

  /**
   * This method is to update a specific {@link Booking}.
   *
   * @param id      {@link Booking} id
   * @param request {@link BookingRequest}
   * @return {@link BookingResponse}
   */
  public BookingResponse update(String id, BookingRequest request) {
    validate(request);
    Booking fetched = repository.findById(id).orElseThrow(() -> {
          log.error(String.format("There are no booking found by id %s", id));
          return new ApiException(HttpStatus.BAD_REQUEST,
              String.format("There are no booking found by id %s", id),
              String.format("There are no booking found by id %s", id),
              null);
        }
    );

    request.setId(id);
    bookingMapper.mapValues(request, fetched);

    repository.save(fetched);
    return bookingMapper.toResponse(fetched);
  }

  private void validate(BookingRequest request) {
    if (StringUtils.isAnyBlank(request.getCustomerId(), request.getRoomId())) {
      log.error("Customer Id, hotel Id, room Id cannot be blank.");
      throw new ApiException(HttpStatus.BAD_REQUEST,
          "Customer Id, room Id cannot be blank.",
          "Customer Id, room Id cannot be blank.", null);
    }
  }
}
