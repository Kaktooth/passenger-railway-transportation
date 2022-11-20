package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Wagon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class WagonServiceTest {
    @Autowired
    private WagonService wagonService;

    @Test
    public void shouldReturnCorrectAllWagon() {
        List<Wagon> list = wagonService.getWagonsByType(UUID.randomUUID());
        Assertions.assertTrue(list.contains(wagonService));
    }
}
