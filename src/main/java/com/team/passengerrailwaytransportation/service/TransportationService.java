package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Station;
import com.team.passengerrailwaytransportation.entities.Transportation;
import com.team.passengerrailwaytransportation.entities.TransportationDTO;
import com.team.passengerrailwaytransportation.repository.StationRepository;
import com.team.passengerrailwaytransportation.repository.TransportationRepository;
import com.team.passengerrailwaytransportation.util.DtoConverter;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransportationService {

  private final StationRepository stationRepository;
  private final TransportationRepository transportationRepository;
  private final DtoConverter dtoConverter;

  @Autowired
  public TransportationService(
      StationRepository stationRepository,
      TransportationRepository transportationRepository,
      DtoConverter dtoConverter) {
    this.stationRepository = stationRepository;
    this.transportationRepository = transportationRepository;
    this.dtoConverter = dtoConverter;
  }

  public TransportationDTO getTransportationById(UUID id) {
    Transportation transportation = transportationRepository.findById(id).get();
    return dtoConverter.convertFrom(transportation);
  }

  public List<TransportationDTO> getTransportationsByCity(String cityName) {
    Station station = stationRepository.findStationByLocation(cityName);
    List<Transportation> transportationList =
        transportationRepository.findAllByFirstStationId(station.getId());
    return dtoConverter.convertFrom(transportationList);
  }

  public List<TransportationDTO> getTransportationsByStation(String stationName) {
    Station station = stationRepository.findStationByName(stationName);
    List<Transportation> transportationList =
        transportationRepository.findAllByFirstStationId(station.getId());
    return dtoConverter.convertFrom(transportationList);
  }

  public List<TransportationDTO> getAllTransportations() {
    List<Transportation> transportationList = transportationRepository.findAll();
    return dtoConverter.convertFrom(transportationList);
  }
}
