package com.hrs.hotel.booking.endpoint.shared.entity;

import com.hrs.hotel.booking.endpoint.hotel.entity.Hotel;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Room entity.<br>This class is to represent all attributes of Room entity.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  private String id;
  private String name;
  private String type;
  private String bedType;
  private String area;
  private BigDecimal price;
  private String detail;

  @OneToOne
  @JoinColumn(name = "hotel_id")
  private Hotel hotel;


  @CreationTimestamp
  private ZonedDateTime createdDate;
  @UpdateTimestamp
  private ZonedDateTime lastUpdatedDate;
}
