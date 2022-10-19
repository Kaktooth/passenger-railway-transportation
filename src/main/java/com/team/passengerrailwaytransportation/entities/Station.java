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
@Table(name = "stations")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Station extends Domain {
  String name;
  String location;

  @Builder
  public Station(UUID id, String name, String location) {
    super(id);
    this.name = name;
    this.location = location;
  }
}
