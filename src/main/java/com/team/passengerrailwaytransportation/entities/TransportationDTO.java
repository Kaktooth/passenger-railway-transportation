package com.team.passengerrailwaytransportation.entities;

import java.sql.Timestamp;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransportationDTO {

  UUID id;

  Train train;

  Station firstStation;

  Station secondStation;

  Timestamp arrivalTime;
}
