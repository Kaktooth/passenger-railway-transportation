package com.team.passengerrailwaytransportation.config.security.jwt;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtConfigurer
    extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  @NonNull
  private final JwtTokenProviderImpl jwtTokenProviderImpl;

  @Override
  public void configure(@NonNull final HttpSecurity httpSecurity) {
    final var jwtTokenFilter = new JwtTokenFilter(jwtTokenProviderImpl);
    httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
