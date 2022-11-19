package com.team.passengerrailwaytransportation.config.security.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

  @NonNull
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public void doFilter(@NonNull final ServletRequest request,
      @NonNull final ServletResponse response,
      @NonNull final FilterChain chain) throws IOException, ServletException {
    assert request instanceof HttpServletRequest;
    final var token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

    if (token != null && jwtTokenProvider.validateToken(token)) {
      final var authentication = jwtTokenProvider.getAuthentication(token);

      if (authentication != null) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    chain.doFilter(request, response);
  }
}
