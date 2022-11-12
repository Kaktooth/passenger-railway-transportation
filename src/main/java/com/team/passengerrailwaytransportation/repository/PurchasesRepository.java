package com.team.passengerrailwaytransportation.repository;

import com.team.passengerrailwaytransportation.entities.Purchase;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasesRepository extends CommonRepository<Purchase> {

  List<Purchase> findAllByUserId(UUID userId);
}
