package com.team.passengerrailwaytransportation.repository;

import com.team.passengerrailwaytransportation.entities.Wagon;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface WagonRepository extends CommonRepository<Wagon> {

  List<Wagon> findAllByTypeId(UUID id);

  List<Wagon> findAllByTrainId(UUID id);

  List<Wagon> findAllByTypeIdAndTrainId(UUID typeId, UUID trainId);
}

