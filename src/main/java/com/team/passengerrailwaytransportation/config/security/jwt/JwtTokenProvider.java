package com.team.passengerrailwaytransportation.config.security.jwt;

import com.team.passengerrailwaytransportation.entities.Role;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;


public interface JwtTokenProvider {

  String createToken(final String email, final Set<Role> roles);

  Authentication getAuthentication(final String token);

  String getEmail(final String token);

  String resolveToken(final HttpServletRequest request);

  boolean validateToken(final String token);
}
