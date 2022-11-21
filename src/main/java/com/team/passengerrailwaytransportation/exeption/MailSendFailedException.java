package com.team.passengerrailwaytransportation.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MailSendFailedException extends RuntimeException {

  public MailSendFailedException(String msg) {
    super(msg);
  }
}
