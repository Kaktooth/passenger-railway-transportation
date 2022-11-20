package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Station;
import com.team.passengerrailwaytransportation.entities.Train;
import com.team.passengerrailwaytransportation.entities.Transportation;
import com.team.passengerrailwaytransportation.entities.Wagon;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  private final TransportationService transportationService;
  private final StationService stationService;
  private final TrainService trainService;
  private final WagonService wagonService;

  public AdminService(
      TransportationService transportationService,
      StationService stationService,
      TrainService trainService,
      WagonService wagonService) {
    this.transportationService = transportationService;
    this.stationService = stationService;
    this.trainService = trainService;
    this.wagonService = wagonService;
  }

  public Train saveTrain(Train train) {
    return trainService.save(train);
  }

  public Wagon saveWagon(Wagon wagon) {
    return wagonService.save(wagon);
  }

  public Station saveStation(Station station) {
    return stationService.save(station);
  }

  public Transportation saveTransportation(Transportation transportation) {
    return transportationService.save(transportation);
  }

  public Train updateTrain(Train train) {
    return trainService.save(train);
  }

  public Wagon updateWagon(Wagon wagon) {
    return wagonService.save(wagon);
  }

  public Station updateStation(Station station) {
    return stationService.save(station);
  }

  public Transportation updateTransportation(Transportation transportation) {
    return transportationService.save(transportation);
  }
}
