package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Wagon;
import com.team.passengerrailwaytransportation.repository.WagonRepository;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class WagonService extends AbstractService<Wagon, WagonRepository> {

  public WagonService(
      WagonRepository repository) {
    super(repository);
  }

  public Wagon getWagonById(UUID id) {
    return repository.findById(id).get();
  }

  public List<Wagon> getWagonsByType(UUID typeId) {
    return repository.findAllByTypeId(typeId);
  }

  public List<Wagon> getWagonsByTrain(UUID trainId) {
    return repository.findAllByTrainId(trainId);
  }

  public List<Wagon> getWagonsByWagonTypeAndTrain(UUID typeId, UUID trainId) {
    return repository.findAllByTypeIdAndTrainId(typeId, trainId);
  }
}
