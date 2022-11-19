package com.team.passengerrailwaytransportation.exeption;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class JwtAuthenticationException extends AuthenticationException {

  @Serial
  private static final long serialVersionUID = 1343665872475435653L;

  public JwtAuthenticationException(String msg) {
    super(msg);
  }
}
