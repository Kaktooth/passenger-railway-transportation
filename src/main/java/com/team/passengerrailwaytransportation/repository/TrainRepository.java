package com.team.passengerrailwaytransportation.repository;

import com.team.passengerrailwaytransportation.entities.Train;
import java.util.List;
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

  @Query(value = "SELECT count(t) FROM tickets t  JOIN trains tr on t.transportation_id = t.transportation_id WHERE tr.id = ?1", nativeQuery = true)
  Integer getPurchasedSeatsByTrainId(UUID id);


  Integer getAvailableSeatsByTrainId(UUID id);

  @Query(value = "SELECT t.place_number FROM tickets t WHERE t.transportation_id = ?1", nativeQuery = true)
  List<Integer> getAllPurchasedSeatsByTransportationId(UUID id);
}

