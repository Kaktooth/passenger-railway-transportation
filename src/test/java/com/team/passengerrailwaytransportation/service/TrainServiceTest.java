package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Train;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class TrainServiceTest {
    @Autowired
    private TrainService trainService;

    @Test
    public void shouldReturnCorrectAllTrain() {
        Train train = createTrain();
        List<Train> trainList = trainService.findAll();
        Assertions.assertTrue(trainList.contains(train));
    }

    private Train createTrain() {
        Train train = new Train();
        return trainService.save(train);
    }
}
