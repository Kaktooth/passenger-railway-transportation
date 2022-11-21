package com.team.passengerrailwaytransportation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.team.passengerrailwaytransportation.entities.Station;
import com.team.passengerrailwaytransportation.helper.TestJsonHelper;
import com.team.passengerrailwaytransportation.repository.StationRepository;
import com.team.passengerrailwaytransportation.service.StationService;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = PassengerRailwayTransportationApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PassengerRailwayTransportationApplicationTests {

  private static final String GET_STATIONS = "/api/transportation/stations";
  private static final String STATION_ID = "765f6195-a3f2-4ea6-8a99-043060164458";
  private static final String RESPONSE = "[{\"id\":\"765f6195-a3f2-4ea6-8a99-043060164458\",\"name\":\"Cherkasy Train Station\",\"location\":\"Cherkasy\"},{\"id\":\"3fd8ed69-fb95-422e-9779-0d217dd67a94\",\"name\":\"Kyiv Central Train Station\",\"location\":\"Kyiv\"},{\"id\":\"9a72f7cb-e0a2-42d8-983c-6abcb7fa9620\",\"name\":\"Boyarka\",\"location\":\"Uman\"}]";

  @LocalServerPort
  private int applicationPort;

  @Autowired
  private StationRepository stationRepository;
  private StationService stationService;

  @BeforeEach
  void initialize() {
    this.stationService = new StationService(stationRepository);
  }

  @Test
  void testUserService_ShouldReturnUserDto() {
    Station station = stationService.findById(
        UUID.fromString(STATION_ID));
    assertNotNull(station);
  }

  @Test
  void getUserById_ShouldReturnUserDto() {
    ResponseEntity<String> response = TestJsonHelper.getForEntity(applicationPort,
        GET_STATIONS);

    assertEquals(RESPONSE, response.getBody());
  }
}
