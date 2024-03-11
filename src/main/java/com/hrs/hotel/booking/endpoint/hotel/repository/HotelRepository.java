package com.hrs.hotel.booking.endpoint.hotel.repository;

import com.hrs.hotel.booking.endpoint.hotel.entity.Hotel;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * This class is to represent all Hotel 's methods interacting with database.
 */
public interface HotelRepository extends CrudRepository<Hotel, String> {

  @Query(value = "Select b.* from Hotel as b"
      + " inner join (select * from Location where country = :country and city = :city limit :limit offset :offset) as l"
      + " on b.location_id = l.id", nativeQuery = true)
  List<Hotel> findByLocationCountryAndCity(String country, String city, int limit, long offset);
}
