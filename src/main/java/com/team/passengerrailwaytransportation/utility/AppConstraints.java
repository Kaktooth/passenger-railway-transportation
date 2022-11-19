package com.team.passengerrailwaytransportation.utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstraints {

  @UtilityClass
  public class Web {

    public final String loginPath = "/api/user/login";

    @UtilityClass
    public class Security {

      public final String secret = "passenger-railway-transportation";
      public final String expirationTime = "36000000";
      public final String tokenPrefix = "Bearer ";
      public final String headerString = "Authorization";
      public final int maxSizeOfPassword = 255;
      public final int minSizeOfPassword = 6;
    }
  }
}
