package com.team.passengerrailwaytransportation.entities;

import java.util.Date;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * Class for response object of any Exception in our app.
 */
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorMessage {

  int status;
  Date date;
  String description;
  String url;
}
