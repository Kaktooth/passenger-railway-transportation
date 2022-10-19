package com.team.passengerrailwaytransportation.entities;

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

  @Builder
  public Transportation(UUID id, UUID trainId, UUID firstStationId, UUID secondStationId) {
    super(id);
    this.trainId = trainId;
    this.firstStationId = firstStationId;
    this.secondStationId = secondStationId;
  }
}
