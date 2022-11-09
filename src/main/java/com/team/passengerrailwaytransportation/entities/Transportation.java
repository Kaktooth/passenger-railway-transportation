package com.team.passengerrailwaytransportation.entities;

import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transportation")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transportation extends Domain {

  UUID trainId;
  UUID firstStationId;
  UUID secondStationId;
  Timestamp arrivalTime;

  @Builder
  public Transportation(UUID id, UUID trainId, UUID firstStationId,
      UUID secondStationId, Timestamp arrivalTime) {
    super(id);
    this.trainId = trainId;
    this.firstStationId = firstStationId;
    this.secondStationId = secondStationId;
    this.arrivalTime = arrivalTime;
  }
}
