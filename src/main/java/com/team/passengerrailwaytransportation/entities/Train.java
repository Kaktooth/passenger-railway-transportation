package com.team.passengerrailwaytransportation.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trains")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Train extends Domain {

  String name;
}
