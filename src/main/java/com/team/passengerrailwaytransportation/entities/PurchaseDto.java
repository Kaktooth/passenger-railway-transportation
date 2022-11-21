package com.team.passengerrailwaytransportation.entities;

import java.sql.Timestamp;
import java.util.UUID;
import lombok.Data;

@Data
public class PurchaseDto {

  UUID userId;

  UUID ticketId;

  Timestamp date;
}
