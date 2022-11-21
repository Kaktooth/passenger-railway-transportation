package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.WagonType;
import com.team.passengerrailwaytransportation.repository.WagonTypeRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WagonTypeService extends AbstractService<WagonType, WagonTypeRepository> {

  public WagonTypeService(
      WagonTypeRepository repository) {
    super(repository);
  }

  public List<WagonType> getAllWagonTypes() {
    return repository.findAll();
  }

  public WagonType getWagonTypeById(UUID id) {
    return repository.findById(id).get();
  }

  public WagonType findWagonTypeByType(String wagonType) {
    return repository.findWagonTypeByType(wagonType);
  }
}
