package com.hrs.hotel.booking.endpoint.booking.entity;

import com.hrs.hotel.booking.endpoint.hotel.entity.Hotel;
import com.hrs.hotel.booking.endpoint.shared.entity.Customer;
import com.hrs.hotel.booking.endpoint.shared.entity.Room;
import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Booking entity. <br> This class is to represents all attributes of booking entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Booking {

  public static final Booking EMPTY = new Booking();

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  private String id;

  private BookingStatus status;

  @OneToOne
  @JoinColumn(name = "room_id", updatable = false)
  private Room room;

  @OneToOne
  @JoinColumn(name = "customer_id", updatable = false)
  private Customer customer;

  private ZonedDateTime checkInDate;
  private ZonedDateTime checkOutDate;

  @CreationTimestamp
  private ZonedDateTime createdDate;
  @UpdateTimestamp
  private ZonedDateTime lastUpdatedDate;
}
