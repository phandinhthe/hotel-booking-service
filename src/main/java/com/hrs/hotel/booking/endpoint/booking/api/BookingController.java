package com.hrs.hotel.booking.endpoint.booking.api;

import com.hrs.hotel.booking.endpoint.booking.api.response.BookingResponse;
import com.hrs.hotel.booking.endpoint.booking.api.request.BookingRequest;
import com.hrs.hotel.booking.endpoint.booking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is to represent all features of Booking endpoint.
 */
@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {

  private final BookingService service;

  public BookingController(final BookingService service) {
    this.service = service;
  }

  /**
   * This api is to create a new Booking.
   * @param request Booking request.
   * @return the created Booking's id.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public  ResponseEntity<String> create(@RequestBody BookingRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request).getId());
  }

  /**
   * This api is to return the booking detail.
   * @param id Booking id.
   * @return {@link BookingResponse}
   */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BookingResponse detail(@PathVariable String id) {
    return service.detail(id);
  }

  /**
   * This api is to update the existing Booking.
   * @param id Booking Id.
   * @param request Booking request.
   * @return {@link BookingResponse} after updated.
   */
  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BookingResponse update(@PathVariable String id, @RequestBody BookingRequest request) {
    return service.update(id, request);
  }
}
