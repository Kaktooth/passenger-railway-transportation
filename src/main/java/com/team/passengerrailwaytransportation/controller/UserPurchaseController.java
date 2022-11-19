package com.team.passengerrailwaytransportation.controller;

import com.team.passengerrailwaytransportation.entities.Purchase;
import com.team.passengerrailwaytransportation.entities.Ticket;
import com.team.passengerrailwaytransportation.service.PurchaseService;
import com.team.passengerrailwaytransportation.service.TicketService;
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


  @PostMapping("/save-ticket")
  public ResponseEntity<Ticket> saveTicket(@Valid @RequestBody Ticket ticket) {
    Ticket result = ticketService.save(ticket);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @PostMapping("/save-purchase")
  public ResponseEntity<Purchase> savePurchase(@Valid @RequestBody Purchase purchase) {
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
