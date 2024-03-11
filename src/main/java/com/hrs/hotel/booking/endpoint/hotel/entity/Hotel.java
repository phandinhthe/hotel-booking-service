package com.hrs.hotel.booking.endpoint.hotel.entity;


import com.hrs.hotel.booking.endpoint.shared.entity.Location;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Hotel entity. <br> This class is to represent all attributes of Hotel entity.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  private String id;
  private String name;
  @OneToOne
  @JoinColumn(name = "location_id")
  private Location location;
  private String phoneNumber;

  @CreationTimestamp
  private ZonedDateTime createdDate;
  @UpdateTimestamp
  private ZonedDateTime lastUpdatedDate;

}
