package com.team.passengerrailwaytransportation.exeption;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 132458323432673843L;

  public UserNotFoundException(String msg) {
    super(msg);
  }
}
