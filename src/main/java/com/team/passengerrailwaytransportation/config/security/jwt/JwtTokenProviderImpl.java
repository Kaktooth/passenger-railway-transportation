package com.team.passengerrailwaytransportation.config.security.jwt;

import com.team.passengerrailwaytransportation.entities.Role;
import com.team.passengerrailwaytransportation.exeption.JwtAuthenticationException;
import com.team.passengerrailwaytransportation.utility.AppConstraints;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProviderImpl implements JwtTokenProvider {

  @NonNull
  private final UserDetailsService userDetailsService;

  @Value(AppConstraints.Web.Security.secret)
  private String secret;

  @Value(AppConstraints.Web.Security.expirationTime)
  private long validityInMilliseconds;

  @PostConstruct
  protected void init() {
    secret = Base64.getEncoder().encodeToString(secret.getBytes());
  }

  @Override
  public String createToken(@NonNull final String email, @NonNull final Set<Role> roles) {
    final var claims = Jwts.claims().setSubject(email);
    claims.put("roles", getRoleNames(roles));

    final var now = new Date();
    final var expirationDate = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expirationDate)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  @Override
  public Authentication getAuthentication(@NonNull final String token) {
    final var userDetails = this.userDetailsService.loadUserByUsername(getEmail(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  @Override
  public String getEmail(@NonNull final String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
  }

  @Override
  public String resolveToken(@NonNull final HttpServletRequest request) {
    final var bearerToken = request.getHeader(AppConstraints.Web.Security.headerString);
    if (bearerToken != null
        && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  @Override
  public boolean validateToken(@NonNull final String token) {
    try {
      final Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

      return !claims.getBody().getExpiration().before(new Date());
    } catch (JwtException | IllegalArgumentException e) {
      throw new JwtAuthenticationException("JWT token is expired or invalid");
    }
  }

  private Set<String> getRoleNames(@NonNull final Set<Role> userRoles) {
    final var result = new HashSet<String>();

    userRoles.forEach(role -> {
      result.add(role.toString());
    });

    return result;
  }
}
