package com.team.passengerrailwaytransportation.controller;

import com.team.passengerrailwaytransportation.service.LiqPayApiService;
import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/buy/ticket")
public class LiqPayController {

  private final LiqPayApiService liqPayApiService;

  public LiqPayController(LiqPayApiService liqPayApiService) {
    this.liqPayApiService = liqPayApiService;
  }

  @PostMapping
  public String buyTicket(@RequestParam("amount") String amount,
      @RequestParam("description") String description, @RequestParam("orderId") String orderId) {
    return liqPayApiService.buyTicket(amount, description, orderId);
  }
}
