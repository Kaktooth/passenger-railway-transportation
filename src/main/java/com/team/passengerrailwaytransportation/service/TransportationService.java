package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Transportation;
import com.team.passengerrailwaytransportation.entities.TransportationDTO;
import com.team.passengerrailwaytransportation.repository.TransportationRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransportationService extends
    AbstractService<Transportation, TransportationRepository> {

  private final ModelMapper modelMapper;

  @Autowired
  public TransportationService(
      ModelMapper modelMapper,
      TransportationRepository repository) {
    super(repository);
    this.modelMapper = modelMapper;
  }

  public TransportationDTO getById(UUID id) {
    return convertEntityToDto(repository.findById(id).get());
  }

  public List<TransportationDTO> getTransportationsByStationId(UUID stationId) {
    return convertEntityListToDto(repository.findAllByFirstStationId(stationId));
  }

  public List<TransportationDTO> getAllTransportations() {
    return convertEntityListToDto(repository.findAll());
  }

  public List<TransportationDTO> convertEntityListToDto(List<Transportation> transportationList) {
    return transportationList.stream()
        .map(this::convertEntityToDto)
        .collect(Collectors.toList());
  }

  public TransportationDTO convertEntityToDto(Transportation transportation) {
    return modelMapper.map(transportation, TransportationDTO.class);
  }
}
