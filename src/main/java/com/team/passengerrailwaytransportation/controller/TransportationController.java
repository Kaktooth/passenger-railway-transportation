package com.team.passengerrailwaytransportation.controller;

import com.team.passengerrailwaytransportation.entities.TransportationDTO;
import com.team.passengerrailwaytransportation.entities.WagonType;
import com.team.passengerrailwaytransportation.service.TransportationDataService;
import com.team.passengerrailwaytransportation.service.WagonTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transportation")
public class TransportationController {

  private final WagonTypeService wagonTypeService;
  private final TransportationDataService transportationDataService;

  @Autowired
  public TransportationController(
      WagonTypeService wagonTypeService,
      TransportationDataService transportationDataService) {
    this.wagonTypeService = wagonTypeService;
    this.transportationDataService = transportationDataService;
  }

  @GetMapping("/wagon-types")
  public List<WagonType> getWagonTypes() {
    return wagonTypeService.getAllWagonTypes();
  }

  @GetMapping("/routes/{cityName}")
  public List<TransportationDTO> getWagonTypes(@PathVariable String cityName) {
    return transportationDataService.getTransportationsWithCurrentCity(cityName);
  }
}
