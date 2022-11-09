package com.team.passengerrailwaytransportation.entities;

import java.math.BigInteger;
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
@Table(name = "tickets")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket extends Domain {

  UUID wagonId;
  Integer placeNumber;
  UUID transportationId;
  BigInteger price;

  @Builder
  public Ticket(UUID id,
      UUID wagonId, Integer placeNumber,
      UUID transportationId, BigInteger price) {
    super(id);
    this.wagonId = wagonId;
    this.placeNumber = placeNumber;
    this.transportationId = transportationId;
    this.price = price;
  }
}