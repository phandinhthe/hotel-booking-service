package com.hrs.hotel.booking.endpoint.booking.repository;

import com.hrs.hotel.booking.endpoint.booking.entity.Booking;
import org.springframework.data.repository.CrudRepository;

/**
 * This class is {@link Booking} repository layer.<br>
 * This includes methods interacting with database to support {@link Booking} domain business.
 */
public interface BookingRepository extends CrudRepository<Booking, String> {

}
