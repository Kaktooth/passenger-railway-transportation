package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Station;
import com.team.passengerrailwaytransportation.entities.Transportation;
import com.team.passengerrailwaytransportation.entities.TransportationDTO;
import com.team.passengerrailwaytransportation.repository.StationRepository;
import com.team.passengerrailwaytransportation.repository.TrainRepository;
import com.team.passengerrailwaytransportation.repository.TransportationRepository;
import com.team.passengerrailwaytransportation.repository.WagonRepository;
import com.team.passengerrailwaytransportation.repository.WagonTypeRepository;
import com.team.passengerrailwaytransportation.util.DtoConverter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransportationDataService {

  private final WagonTypeRepository wagonTypeRepository;
  private final StationRepository stationRepository;
  private final TrainRepository trainRepository;
  private final WagonRepository wagonRepository;
  private final TransportationRepository transportationRepository;
  private final DtoConverter dtoConverter;

  @Autowired
  public TransportationDataService(
      WagonTypeRepository wagonTypeRepository,
      StationRepository stationRepository,
      TrainRepository trainRepository,
      WagonRepository wagonRepository,
      TransportationRepository transportationRepository,
      DtoConverter dtoConverter) {
    this.wagonTypeRepository = wagonTypeRepository;
    this.stationRepository = stationRepository;
    this.trainRepository = trainRepository;
    this.wagonRepository = wagonRepository;
    this.transportationRepository = transportationRepository;
    this.dtoConverter = dtoConverter;
  }

  public List<TransportationDTO> getTransportationsWithCurrentCity(String cityName) {
    Station station = stationRepository.findStationByLocation(cityName);
    List<Transportation> transportationList =
        transportationRepository.findAllByFirstStationId(station.getId());
    return dtoConverter.convertFrom(transportationList);
  }

}
