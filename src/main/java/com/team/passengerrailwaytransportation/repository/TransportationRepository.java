package com.team.passengerrailwaytransportation.repository;

import com.team.passengerrailwaytransportation.entities.Transportation;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportationRepository extends CommonRepository<Transportation> {

  List<Transportation> findAllByFirstStationId(UUID stationId);
}

