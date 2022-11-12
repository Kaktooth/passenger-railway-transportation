package com.team.passengerrailwaytransportation.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "wagon_types")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WagonType extends Domain {

  String type;

  Integer seatNumber;
}
