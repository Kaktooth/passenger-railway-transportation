package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Train;
import com.team.passengerrailwaytransportation.entities.Wagon;
import com.team.passengerrailwaytransportation.repository.TrainRepository;
import com.team.passengerrailwaytransportation.repository.WagonRepository;
import com.team.passengerrailwaytransportation.repository.WagonTypeRepository;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TrainService {

  private final TrainRepository trainRepository;
  private final WagonRepository wagonRepository;
  private final WagonTypeRepository wagonTypeRepository;

  public TrainService(
      TrainRepository trainRepository,
      WagonRepository wagonRepository,
      WagonTypeRepository wagonTypeRepository) {
    this.trainRepository = trainRepository;
    this.wagonRepository = wagonRepository;
    this.wagonTypeRepository = wagonTypeRepository;
  }

  public Train getTrainById(UUID id) {
    return trainRepository.findById(id).get();
  }

  public Integer getSeatsNumberByTrainId(UUID id) {
    List<Wagon> wagons = wagonRepository.findAllByTrainId(id);
    Integer trainSeatsNumber = 0;
    for (var wagon : wagons) {
      trainSeatsNumber += wagonTypeRepository.findById(wagon.getTypeId()).get().getSeatNumber();
    }
    return trainSeatsNumber;
  }
}
