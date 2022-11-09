package com.team.passengerrailwaytransportation.entities;

import java.security.Timestamp;
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
@Table(name = "buying_history")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Station extends Domain {

  UUID ticketId;
  Timestamp date;

  @Builder
  public Station(UUID id, UUID ticketId, Timestamp date) {
    super(id);
    this.ticketId = ticketId;
    this.date = date;
  }
}
