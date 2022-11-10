package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Wagon;
import com.team.passengerrailwaytransportation.repository.WagonRepository;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class WagonService {

  private final WagonRepository wagonRepository;

  public WagonService(WagonRepository wagonRepository) {
    this.wagonRepository = wagonRepository;
  }

  public Wagon getWagonById(UUID id) {
    return wagonRepository.findById(id).get();
  }

  public List<Wagon> getWagonsByType(UUID typeId) {
    return wagonRepository.findAllByTypeId(typeId);
  }

  public List<Wagon> getWagonsByTrain(UUID trainId) {
    return wagonRepository.findAllByTrainId(trainId);
  }

  public List<Wagon> getWagonsByWagonTypeAndTrain(UUID typeId, UUID trainId) {
    return wagonRepository.findAllByTypeIdAndTrainId(typeId, trainId);
  }
}
