package com.team.passengerrailwaytransportation.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@Entity
@Table(name = "stations")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Station extends Domain {

  String name;

  String location;
}
