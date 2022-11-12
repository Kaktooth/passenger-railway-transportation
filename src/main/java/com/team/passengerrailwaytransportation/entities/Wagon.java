package com.team.passengerrailwaytransportation.entities;

import java.util.UUID;
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
@Table(name = "wagons")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Wagon extends Domain {

  UUID typeId;

  UUID trainId;
}
