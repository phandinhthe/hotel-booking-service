package com.hrs.hotel.booking.endpoint.booking.repository;

import com.hrs.hotel.booking.endpoint.booking.entity.Booking;
import com.hrs.hotel.booking.endpoint.booking.entity.BookingStatus;
import com.hrs.hotel.booking.endpoint.shared.entity.Customer;
import com.hrs.hotel.booking.endpoint.hotel.entity.Hotel;
import com.hrs.hotel.booking.endpoint.shared.entity.Room;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@ActiveProfiles("test")
public class BookingRepositoryTest {


  @Autowired
  private BookingRepository bookingRepository;

  @AfterEach
  public void reset() {
    bookingRepository.deleteAll();
  }

  @Test
  public void testInsert_WhenHappyCase_thenReturnInserted() {
    Booking booking = data();

    Booking inserted = bookingRepository.save(booking);
    Assertions.assertNotNull(inserted);
    Assertions.assertEquals(1, bookingRepository.count());
  }

  @Test
  public void testUpdate_WhenHappyCase_thenReturnInserted() {
    Booking booking = data();
    Booking inserted = bookingRepository.save(booking);
    ZonedDateTime insertedCheckIn = inserted.getCheckInDate();

    inserted.setCheckInDate(ZonedDateTime.now());
    Booking updated = bookingRepository.save(inserted);
    Assertions.assertNotEquals(insertedCheckIn, updated.getCheckInDate());
  }


  private Booking data() {
    return Booking.builder()
        .customer(Customer.builder().id("018e2210-b91d-7da8-a5b7-f6e0651b1d20").build())
        .room(Room.builder().id("018e2204-f44e-7952-a810-3a4a4f3078ec").build())
        .status(BookingStatus.PROCESSING)
        .checkInDate(ZonedDateTime.now().minusDays(7))
        .checkOutDate(ZonedDateTime.now().plusDays(7))
        .build();
  }
}
