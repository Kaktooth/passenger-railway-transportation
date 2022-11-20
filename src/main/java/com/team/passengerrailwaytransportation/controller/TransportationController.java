package com.team.passengerrailwaytransportation.controller;

import com.team.passengerrailwaytransportation.entities.Station;
import com.team.passengerrailwaytransportation.entities.TransportationDTO;
import com.team.passengerrailwaytransportation.entities.Wagon;
import com.team.passengerrailwaytransportation.entities.WagonType;
import com.team.passengerrailwaytransportation.service.StationService;
import com.team.passengerrailwaytransportation.service.TrainService;
import com.team.passengerrailwaytransportation.service.TransportationService;
import com.team.passengerrailwaytransportation.service.WagonService;
import com.team.passengerrailwaytransportation.service.WagonTypeService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/transportation")
public class TransportationController {


  private final StationService stationService;
  private final TrainService trainService;
  private final WagonService wagonService;
  private final WagonTypeService wagonTypeService;
  private final TransportationService transportationService;


  @GetMapping("/wagon-types")
  public List<WagonType> getWagonTypes() {
    return wagonTypeService.getAllWagonTypes();
  }

  @GetMapping("/stations")
  public List<Station> getAllStations() {
    return stationService.findAll();
  }

  @GetMapping("/stations/locations")
  public List<String> getAllStationLocations() {
    return stationService.findAllStationLocations();
  }

  @GetMapping("/routes/{cityName}")
  public List<TransportationDTO> getTransportationByCityName(@PathVariable String cityName) {
    Station station = stationService.findStationByLocation(cityName);
    return transportationService.getTransportationsByStationId(station.getId());
  }

  @GetMapping("/transportation/{id}")
  public TransportationDTO getTransportationById(@PathVariable UUID id) {
    return transportationService.getById(id);
  }

  @GetMapping("/routes/{id}/train/getSeatsNumber")
  public Integer getTransportationTrainSeatsNumber(@PathVariable UUID id) {
    TransportationDTO transportation = transportationService.getById(id);
    return trainService.getSeatsNumberByTrainId(transportation.getTrain().getId());
  }

  @GetMapping("/routes/{id}/train/getPurchasedSeats")
  Integer getPurchasedSeatsByTrainId(UUID id) {
    TransportationDTO transportation = transportationService.getById(id);
    return trainService.getPurchasedSeatsByTrainId(transportation.getTrain().getId());
  }

  @GetMapping("/routes/{id}/train/getAvailableSeats")
  Integer getAvailableSeatsByTrainId(UUID id) {
    TransportationDTO transportation = transportationService.getById(id);
    return trainService.getAvailableSeatsWithTrainId(transportation.getTrain().getId());
  }

  @GetMapping("/routes/{id}/train/getAllPurchasedSeats")
  List<Integer> getAllPurchasedSeatsByTransportationId(UUID id) {
    return trainService.getAllPurchasedSeatsByTransportationId(id);
  }

  @GetMapping("/routes/{id}/train/wagons")
  public List<Wagon> getTransportationWagons(@PathVariable UUID id) {
    TransportationDTO transportation = transportationService.getById(id);
    return wagonService.getWagonsByTrain(transportation.getTrain().getId());
  }

  @GetMapping("/routes/{id}/train/wagons-types/{wagonTypeId}")
  public List<Wagon> getWagonsType(@PathVariable UUID id,
      @PathVariable UUID wagonTypeId) {
    TransportationDTO transportation = transportationService.getById(id);
    return wagonService.getWagonsByWagonTypeAndTrain(wagonTypeId,
        transportation.getTrain().getId());
  }


}
