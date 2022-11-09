package com.team.passengerrailwaytransportation.exeption;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {

  String message;
  Map<String, String> details;

  public ApiError(String message, Map<String, String> details) {
    super();
    this.message = message;
    this.details = details;
  }
}

