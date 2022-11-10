package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.WagonType;
import com.team.passengerrailwaytransportation.repository.WagonTypeRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WagonTypeService {

  private final WagonTypeRepository wagonTypeRepository;

  @Autowired
  public WagonTypeService(WagonTypeRepository wagonTypeRepository) {
    this.wagonTypeRepository = wagonTypeRepository;
  }

  public List<WagonType> getAllWagonTypes() {
    return wagonTypeRepository.findAll();
  }

  public WagonType getWagonTypeById(UUID id) {
    return wagonTypeRepository.findById(id).get();
  }
}
