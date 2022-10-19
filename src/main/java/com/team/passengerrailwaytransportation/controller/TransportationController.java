package com.team.passengerrailwaytransportation.controller;

import com.team.passengerrailwaytransportation.entities.WagonType;
import com.team.passengerrailwaytransportation.service.WagonTypeService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/transportation")
public class TransportationController {

  private final WagonTypeService wagonTypeService;

  @Autowired
  public TransportationController(
      WagonTypeService wagonTypeService) {
    this.wagonTypeService = wagonTypeService;
  }

  @GetMapping("/wagon-types")
  public List<WagonType> getWagonTypes() {
    log.info("getting wagon types");
    return wagonTypeService.getAllWagonTypes();
  }
}
