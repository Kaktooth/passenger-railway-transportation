package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.WagonType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class WagonTypeServiceTest {
    @Autowired
    private WagonTypeService wagonTypeService;

    @Test
    public void shouldReturnCorrectAllWagonTypes() {
        WagonType wagonType = createWagonTypes();

        List<WagonType> list = wagonTypeService.getAllWagonTypes();
        Assertions.assertTrue(list.contains(wagonType));
    }

    private WagonType createWagonTypes() {
        WagonType wagonType = new WagonType("String", 1);
        return wagonTypeService.save(wagonType);
    }
}
