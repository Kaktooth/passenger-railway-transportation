package com.team.passengerrailwaytransportation.entities;

import java.sql.Timestamp;
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

  Train train;

  Station firstStation;

  Station secondStation;

  Timestamp arrivalTime;
}
