package com.hrs.hotel.booking.endpoint.shared.repository;

import com.hrs.hotel.booking.endpoint.shared.entity.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, String> {

}
