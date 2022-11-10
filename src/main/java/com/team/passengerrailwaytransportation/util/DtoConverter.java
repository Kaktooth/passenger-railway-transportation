package com.team.passengerrailwaytransportation.util;

import com.team.passengerrailwaytransportation.entities.Transportation;
import com.team.passengerrailwaytransportation.entities.TransportationDTO;
import com.team.passengerrailwaytransportation.repository.StationRepository;
import com.team.passengerrailwaytransportation.repository.TrainRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

  private final TrainRepository trainRepository;
  private final StationRepository stationRepository;

  @Autowired
  public DtoConverter(TrainRepository trainRepository,
      StationRepository stationRepository) {
    this.trainRepository = trainRepository;
    this.stationRepository = stationRepository;
  }

  public TransportationDTO convertFrom(Transportation transportation) {
    return TransportationDTO
        .builder()
        .train(trainRepository.findById(transportation.getTrainId()).get())
        .firstStation(stationRepository.findById(transportation.getFirstStationId()).get())
        .secondStation(stationRepository.findById(transportation.getSecondStationId()).get())
        .arrivalTime(transportation.getArrivalTime())
        .build();
  }

  public List<TransportationDTO> convertFrom(List<Transportation> transportationList) {
    List<TransportationDTO> dtoList = new ArrayList<>();
    for (var transportation : transportationList) {
      dtoList.add(
          TransportationDTO
              .builder()
              .train(trainRepository.findById(transportation.getTrainId()).get())
              .firstStation(stationRepository.findById(transportation.getFirstStationId()).get())
              .secondStation(stationRepository.findById(transportation.getSecondStationId()).get())
              .arrivalTime(transportation.getArrivalTime())
              .build());
    }
    return dtoList;
  }
}
