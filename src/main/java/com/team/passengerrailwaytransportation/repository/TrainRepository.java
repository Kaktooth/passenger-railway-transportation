package com.team.passengerrailwaytransportation.repository;

import com.team.passengerrailwaytransportation.entities.Train;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends CommonRepository<Train> {

  @Query(value =
      "SELECT sum(wt.seat_number) FROM wagons w JOIN  wagon_types wt on w.type_id = wt.id "
          + " WHERE w.train_id = ?1 ",
      nativeQuery = true)
  Integer getSeatsNumberByTrainId(UUID id);

}

