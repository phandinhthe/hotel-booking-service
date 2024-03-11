package com.hrs.hotel.booking.endpoint.shared.repository;

import com.hrs.hotel.booking.endpoint.shared.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {

}
