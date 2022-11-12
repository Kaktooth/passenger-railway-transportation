package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Train;
import com.team.passengerrailwaytransportation.repository.TrainRepository;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TrainService extends AbstractService<Train, TrainRepository> {

  public TrainService(TrainRepository repository) {
    super(repository);
  }

  public Integer getSeatsNumberByTrainId(UUID id) {
    return repository.getSeatsNumberByTrainId(id);
  }
}
