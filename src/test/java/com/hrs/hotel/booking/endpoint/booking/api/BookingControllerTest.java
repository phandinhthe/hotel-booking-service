package com.hrs.hotel.booking.endpoint.booking.api;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrs.hotel.booking.endpoint.booking.api.request.BookingRequest;
import com.hrs.hotel.booking.endpoint.booking.api.response.BookingResponse;
import com.hrs.hotel.booking.endpoint.booking.entity.BookingStatus;
import com.hrs.hotel.booking.endpoint.booking.repository.BookingRepository;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookingControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private BookingRepository repository;

  @AfterEach
  void reset() {
    repository.deleteAll();
  }

  private BookingRequest request() {
    return BookingRequest.builder()
        .customerId("018e2210-b91d-7da8-a5b7-f6e0651b1d20")
        .roomId("018e2204-f44e-7952-a810-3a4a4f3078ec")
        .status(BookingStatus.PROCESSING)
        .checkInDate(ZonedDateTime.now().plusDays(7L))
        .checkOutDate(ZonedDateTime.now().plusDays(14L))
        .build();
  }

  @Test
  public void testCreateBooking_WhenHappyCase_ThenReturnInsertedId()
      throws Exception {
    mockMvc.perform(post("/api/v1/bookings")
            .contentType("application/json")
            .content(mapper.writeValueAsString(request())))
        .andExpect(status().isCreated());
  }

  @Test
  public void testGetBookingDetail_WhenHappyCase_ThenReturnSavedBooking()
      throws Exception {

    System.out.print(mapper.writeValueAsString(request()));
    String id = mockMvc.perform(post("/api/v1/bookings")
            .contentType("application/json")
            .content(mapper.writeValueAsString(request())))
        .andExpect(status().isCreated())
        .andReturn().getResponse().getContentAsString();

    mockMvc.perform(get("/api/v1/bookings/" + id)
            .contentType("application/json"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(notNullValue())))
        .andExpect(jsonPath("$.hotel", is(notNullValue())))
        .andExpect(jsonPath("$.room", is(notNullValue())))
        .andExpect(jsonPath("$.customer", is(notNullValue())));
  }

  @Test
  public void testUpdateBooking_WhenHappyCase_ThenReturnInsertedId()
      throws Exception {
    BookingRequest request = request();
    String id = mockMvc.perform(post("/api/v1/bookings")
            .contentType("application/json")
            .content(mapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andReturn().getResponse().getContentAsString();

    request.setCheckInDate(ZonedDateTime.of(3000, 12, 12, 0, 0, 0, 0, ZoneId.systemDefault()));
    request.setCheckOutDate(request.getCheckInDate().plusDays(7));
    var byteResponse = mockMvc.perform(patch("/api/v1/bookings/" + id)
            .contentType("application/json")
            .content(mapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(equalTo(id))))
        .andExpect(jsonPath("$.room.id", is(equalTo(request.getRoomId()))))
        .andExpect(jsonPath("$.customer.id", is(equalTo(request.getCustomerId()))))

        .andReturn().getResponse().getContentAsByteArray();
    BookingResponse response = mapper.readValue(byteResponse, BookingResponse.class);

    Assertions.assertEquals(3000, response.getCheckInDate().getYear());

  }

  @Test
  public void testCreateBooking_WhenRoomIdIsNotProvided_ThenReturnBadRequest()
      throws Exception {

    BookingRequest request = request();
    request.setRoomId(null);
    mockMvc.perform(post("/api/v1/bookings")
            .contentType("application/json")
            .content(mapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.clientMessage",
            is(equalTo("Customer Id, room Id cannot be blank."))))
        .andExpect(jsonPath("$.internalMessage",
            is(equalTo("Customer Id, room Id cannot be blank."))));
  }

  @Test
  public void testGetBookingDetail_WhenRoomIdIsBlank_ThenReturnBadRequest()
      throws Exception {

    BookingRequest request = request();
    request.setCustomerId(" ");
    mockMvc.perform(post("/api/v1/bookings")
            .contentType("application/json")
            .content(mapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.clientMessage",
            is(equalTo("Customer Id, room Id cannot be blank."))))
        .andExpect(jsonPath("$.internalMessage",
            is(equalTo("Customer Id, room Id cannot be blank."))));
  }
}
