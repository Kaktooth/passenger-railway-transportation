package com.team.passengerrailwaytransportation.entities;

import java.util.UUID;
import lombok.Data;

@Data
public class TicketDto {

  UUID wagonId;

  UUID userId;

  Integer placeNumber;

  UUID transportationId;

  Integer price;
}
