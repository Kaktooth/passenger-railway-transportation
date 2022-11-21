package com.team.passengerrailwaytransportation.repository;

import com.team.passengerrailwaytransportation.entities.WagonType;
import org.springframework.stereotype.Repository;

@Repository
public interface WagonTypeRepository extends CommonRepository<WagonType> {

  WagonType findWagonTypeByType(String wagonType);
}
