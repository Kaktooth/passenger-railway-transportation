package com.team.passengerrailwaytransportation;

import static com.team.passengerrailwaytransportation.helper.TestDBHelper.executeDBQuery;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import com.opentable.db.postgres.junit.SingleInstancePostgresRule;
import com.team.passengerrailwaytransportation.entities.Station;
import com.team.passengerrailwaytransportation.helper.TestJsonHelper;
import com.team.passengerrailwaytransportation.repository.StationRepository;
import com.team.passengerrailwaytransportation.service.StationService;
import java.util.UUID;
import javax.sql.DataSource;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = PassengerRailwayTransportationApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PassengerRailwayTransportationApplicationTests {


  private static final String CREATE_TABLE = "db/migration/V20220925143347__add_stations_table.sql";
  private static final String INSERT_DATA = "db/migration/V20221109185924__insert_station_values.sql";
  private static final String DROP_TABLE = "db/other/V20221121210945__drop_stations.sql";
  private static final String GET_STATIONS = "/api/transportation/stations";
  private static final String STATION_ID = "765f6195-a3f2-4ea6-8a99-043060164458";
  private static final String RESPONSE = "{\"name\":\"Vova\",\"surname\":\"Bezridnyi\",\"age\":20}";

  @Rule
  public PreparedDbRule db =
      EmbeddedPostgresRules.preparedDatabase(
          FlywayPreparer.forClasspathLocation("db/railway"));

  @LocalServerPort
  private int applicationPort;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private StationRepository stationRepository;
  private StationService stationService;

  @BeforeEach
  void initialize() {
    executeDBQuery(dataSource, CREATE_TABLE);
    executeDBQuery(dataSource, INSERT_DATA);
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
        GET_STATIONS + STATION_ID);

    assertEquals(RESPONSE, response.getBody());
  }

  @AfterEach
  void deleteData() {
    executeDBQuery(dataSource, DROP_TABLE);
  }
}
