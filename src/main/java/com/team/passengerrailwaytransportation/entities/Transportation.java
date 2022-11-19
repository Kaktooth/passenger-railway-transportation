package com.team.passengerrailwaytransportation.entities;

import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
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
@Table(name = "transportation")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transportation extends Domain {

  @OneToOne
  Train train;

  @OneToOne
  Station firstStation;

  @OneToOne
  Station secondStation;

  Timestamp arrivalTime;
}
