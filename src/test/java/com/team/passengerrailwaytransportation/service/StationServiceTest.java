package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Station;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StationServiceTest {
    @Autowired
    private StationService service;

    @Test
    public void shouldReturnCorrectAllStation() {
        Station station = createStation();

        List<Station> list = service.findAll();
        Assertions.assertTrue(list.contains(station));
    }

    private Station createStation() {
        Station station = new Station();
        return service.save(station);
    }
}
