package com.team.passengerrailwaytransportation.repository;

import com.team.passengerrailwaytransportation.entities.Station;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends CommonRepository<Station> {

  Station findStationByLocation(String location);

  Station findStationByName(String stationName);

  @Query(value = "SELECT location FROM stations", nativeQuery = true)
  List<String> findAllStationLocations();
}

