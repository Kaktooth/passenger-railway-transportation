package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Transportation;
import com.team.passengerrailwaytransportation.entities.WagonType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TransportationServiceTest {
    @Autowired
    private TransportationService service;

    @Test
    public void shouldReturnCorrectAllTransportation() {
        Transportation transportation = createTransportation();

        List<Transportation> list = service.findAll();
        Assertions.assertTrue(list.contains(transportation));
    }

    private Transportation createTransportation() {
        Transportation transportation = new Transportation();
        return service.save(transportation);
    }
}
