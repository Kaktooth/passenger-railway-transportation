package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Station;
import com.team.passengerrailwaytransportation.repository.StationRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StationService {

  private final StationRepository stationRepository;

  public StationService(
      StationRepository stationRepository) {
    this.stationRepository = stationRepository;
  }

  public Station getStationById(UUID id) {
    return stationRepository.findById(id).get();
  }
}
