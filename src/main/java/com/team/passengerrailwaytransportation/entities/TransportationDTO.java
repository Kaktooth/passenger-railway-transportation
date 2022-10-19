package com.team.passengerrailwaytransportation.entities;

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

  @Builder
  public TransportationDTO(Train train, Station firstStation, Station secondStation) {
    this.train = train;
    this.firstStation = firstStation;
    this.secondStation = secondStation;
  }
}
