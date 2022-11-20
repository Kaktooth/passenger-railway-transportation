package com.team.passengerrailwaytransportation.entities;

import java.math.BigInteger;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket extends Domain {

  @OneToOne
  Wagon wagon;

  UUID userId;

  @Min(value = 0, message = "The value must be positive")
  Integer placeNumber;

  @OneToOne
  Transportation transportation;

  BigInteger price;
}