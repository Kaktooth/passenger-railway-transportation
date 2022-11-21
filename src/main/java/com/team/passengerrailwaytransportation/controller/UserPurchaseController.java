package com.team.passengerrailwaytransportation.controller;

import com.team.passengerrailwaytransportation.entities.Purchase;
import com.team.passengerrailwaytransportation.entities.PurchaseDto;
import com.team.passengerrailwaytransportation.entities.Ticket;
import com.team.passengerrailwaytransportation.entities.TicketDto;
import com.team.passengerrailwaytransportation.entities.Transportation;
import com.team.passengerrailwaytransportation.entities.Wagon;
import com.team.passengerrailwaytransportation.service.PurchaseService;
import com.team.passengerrailwaytransportation.service.TicketService;
import com.team.passengerrailwaytransportation.service.TransportationService;
import com.team.passengerrailwaytransportation.service.WagonService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/")
@CrossOrigin(origins = "http://localhost:3000")
public class UserPurchaseController {

  private final PurchaseService purchaseService;
  private final TicketService ticketService;
  private final WagonService wagonService;
  private final TransportationService transportationService;

  @PostMapping("/save-ticket")
  public ResponseEntity<Ticket> saveTicket(@Valid @RequestBody TicketDto ticketDto) {
    Wagon wagon = wagonService.findById(ticketDto.getWagonId());
    Transportation transportation = transportationService.findById(ticketDto.getTransportationId());
    Ticket ticket = new Ticket(wagon, ticketDto.getUserId(), ticketDto.getPlaceNumber(),
        transportation, ticketDto.getPrice());
    Ticket result = ticketService.save(ticket);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @PostMapping("/save-purchase")
  public ResponseEntity<Purchase> savePurchase(@Valid @RequestBody PurchaseDto purchaseDto) {
    Ticket ticket = ticketService.findById(purchaseDto.getTicketId());
    Purchase purchase = new Purchase(purchaseDto.getUserId(), ticket, purchaseDto.getDate());
    Purchase result = purchaseService.save(purchase);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @GetMapping("/user/{userId}/purchase-history")
  public List<Purchase> getUserPurchaseHistory(@PathVariable UUID userId) {
    return purchaseService.getUsersPurchaseHistory(userId);
  }

  @GetMapping("/user/{userId}/tickets")
  public List<Ticket> getUserTickets(@PathVariable UUID userId) {
    return ticketService.getUsersTickets(userId);
  }
}
