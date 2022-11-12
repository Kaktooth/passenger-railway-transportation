package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Purchase;
import com.team.passengerrailwaytransportation.repository.PurchasesRepository;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PurchaseService extends
    AbstractService<Purchase, PurchasesRepository> {

  PurchaseService(PurchasesRepository repository) {
    super(repository);
  }

  public List<Purchase> getUsersPurchaseHistory(UUID userId) {
    return repository.findAllByUserId(userId);
  }
}
