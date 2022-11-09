package com.team.passengerrailwaytransportation.entities;

import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransportationDTO {

  final Train train;
  final Station firstStation;
  final Station secondStation;
  final Timestamp arrivalTime;

  @Builder
  public TransportationDTO(Train train, Station firstStation,
      Station secondStation, Timestamp arrivalTime) {
    this.train = train;
    this.firstStation = firstStation;
    this.secondStation = secondStation;
    this.arrivalTime = arrivalTime;
  }
}
