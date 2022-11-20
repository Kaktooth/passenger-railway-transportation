package com.team.passengerrailwaytransportation.controller;

import com.team.passengerrailwaytransportation.service.OperatorService;
import javax.mail.MessagingException;
import javax.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/operator")
public class OperatorController {

  private final OperatorService operatorService;

  public OperatorController(
      OperatorService operatorService) {
    this.operatorService = operatorService;
  }

//  @PreAuthorize()
  @PostMapping("/sendMessage")
  public HttpStatus sendMessage(@PathParam("message") String message,
      @PathParam("subject") String subject, @PathParam("to") String to) throws MessagingException {
    operatorService.sendMessage(message, subject, to);
    return HttpStatus.OK;
  }
}
