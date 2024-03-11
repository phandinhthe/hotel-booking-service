package com.hrs.hotel.booking.endpoint.shared.entity;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Customer entity. <br>This class is to represent all attributes of Customer entity.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  private String id;

  private String firstName;
  private String lastName;
  private String middleName;
  private String phoneNumber;
  private String address;

  @CreationTimestamp
  private ZonedDateTime createdDate;
  @UpdateTimestamp
  private ZonedDateTime lastUpdatedDate;

}
