package com.team.passengerrailwaytransportation.controller;

import com.team.passengerrailwaytransportation.entities.TransportationDTO;
import com.team.passengerrailwaytransportation.entities.Wagon;
import com.team.passengerrailwaytransportation.entities.WagonType;
import com.team.passengerrailwaytransportation.service.TrainService;
import com.team.passengerrailwaytransportation.service.TransportationService;
import com.team.passengerrailwaytransportation.service.WagonService;
import com.team.passengerrailwaytransportation.service.WagonTypeService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transportation")
public class TransportationController {

  private final TrainService trainService;
  private final WagonService wagonService;
  private final WagonTypeService wagonTypeService;
  private final TransportationService transportationService;

  @Autowired
  public TransportationController(
      TrainService trainService,
      WagonService wagonService,
      WagonTypeService wagonTypeService,
      TransportationService transportationService) {
    this.trainService = trainService;
    this.wagonService = wagonService;
    this.wagonTypeService = wagonTypeService;
    this.transportationService = transportationService;
  }

  @GetMapping("/wagon-types")
  public List<WagonType> getWagonTypes() {
    return wagonTypeService.getAllWagonTypes();
  }

  @GetMapping("/routes/{cityName}")
  public List<TransportationDTO> getTransportationByCityName(@PathVariable String cityName) {
    return transportationService.getTransportationsByCity(cityName);
  }

  @GetMapping("/routes/{id}")
  public TransportationDTO getTransportationById(@PathVariable UUID id) {
    return transportationService.getTransportationById(id);
  }

  @GetMapping("/routes/{id}/train/getSeatsNumber")
  public Integer getTransportationTrain(@PathVariable UUID id) {
    TransportationDTO transportation = transportationService.getTransportationById(id);
    return trainService.getSeatsNumberByTrainId(transportation.getTrain().getId());
  }

  @GetMapping("/routes/{id}/train/wagons")
  public List<Wagon> getTransportationWagons(@PathVariable UUID id) {
    TransportationDTO transportation = transportationService.getTransportationById(id);
    return wagonService.getWagonsByTrain(transportation.getTrain().getId());
  }

  @GetMapping("/routes/{id}/train/wagons-types/{wagonTypeId}")
  public List<Wagon> getWagonsType(@PathVariable UUID id,
      @PathVariable UUID wagonTypeId) {
    TransportationDTO transportation = transportationService.getTransportationById(id);
    return wagonService.getWagonsByWagonTypeAndTrain(wagonTypeId,
        transportation.getTrain().getId());
  }
}
