package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Station;
import com.team.passengerrailwaytransportation.repository.StationRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StationService extends AbstractService<Station, StationRepository> {

  public StationService(StationRepository repository) {
    super(repository);
  }

  public Station findStationByLocation(String location) {
    return repository.findStationByLocation(location);
  }

  public Station findStationByName(String stationName) {
    return repository.findStationByName(stationName);
  }

  public List<String> findAllStationLocations() {
    return repository.findAllStationLocations();
  }
}
