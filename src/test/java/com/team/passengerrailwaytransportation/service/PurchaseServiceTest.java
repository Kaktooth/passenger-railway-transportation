package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Purchase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PurchaseServiceTest {
    @Autowired
    private PurchaseService purchaseService;

    @Test
    public void shouldReturnCorrectAllPurchase() {
        Purchase purchase = createPurchase();
        List<Purchase> list = purchaseService.findAll();
        Assertions.assertTrue(list.contains(purchase));
    }

    private Purchase createPurchase() {
        Purchase purchase = new Purchase();
        return purchaseService.save(purchase);
    }
}
