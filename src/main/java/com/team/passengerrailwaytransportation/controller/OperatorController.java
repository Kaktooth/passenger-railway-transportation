package com.team.passengerrailwaytransportation.controller;

import com.team.passengerrailwaytransportation.exeption.MailSendFailedException;
import com.team.passengerrailwaytransportation.service.OperatorService;
import javax.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/operator")
public class OperatorController {

  private final OperatorService operatorService;

  public OperatorController(
      OperatorService operatorService) {
    this.operatorService = operatorService;
  }

  @PostMapping("/sendMessage")
  @PreAuthorize("hasRole('OPERATOR')")
  public ResponseEntity<String> sendMessage(@RequestParam("message") String message,
      @RequestParam("subject") String subject, @RequestParam("to") String to) {
    try {
      operatorService.sendMessage(message, subject, to);
    } catch (Exception ex) {
      throw new MailSendFailedException(ex.getMessage());
    }
    return ResponseEntity.ok("Message sent.");
  }
}
