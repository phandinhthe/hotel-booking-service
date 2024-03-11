package com.hrs.hotel.booking.endpoint.hotel.api;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrs.hotel.booking.endpoint.hotel.api.response.HotelResponse;
import java.util.List;
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
public class HotelControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testGetHotels_WhenHappyCase_ThenReturnSavedBooking()
      throws Exception {
    byte[] byteResponse = mockMvc.perform(
            get("/api/v1/hotels?country=Thailand&city=Bangkok&City&page=0&size=2")
                .contentType("application/json"))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsByteArray();
    List<HotelResponse> responses =
        objectMapper.readValue(byteResponse, new TypeReference<List<HotelResponse>>() {
        });
    Assertions.assertEquals(2, responses.size());
  }

  @Test
  public void testGetHotels_WhenNotFoundHotel_ThenReturnBadRequest()
      throws Exception {
    mockMvc.perform(
            get("/api/v1/hotels?country=NoCountry&city=Bangkok&City&page=0&size=2")
                .contentType("application/json"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.clientMessage",
            equalTo("There are no hotels found by country NoCountry and city Bangkok")))
        .andExpect(jsonPath("$.internalMessage",
            equalTo("There are no hotels found by country NoCountry and city Bangkok")));
    ;
  }
}
