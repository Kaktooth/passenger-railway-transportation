package com.team.passengerrailwaytransportation.config.security;

import com.team.passengerrailwaytransportation.config.security.handler.FilterChainExceptionHandler;
import com.team.passengerrailwaytransportation.config.security.jwt.JwtConfigurer;
import com.team.passengerrailwaytransportation.config.security.jwt.JwtTokenProviderImpl;
import com.team.passengerrailwaytransportation.utility.AppConstraints;
import com.team.passengerrailwaytransportation.utility.AppConstraints.Web;
import javax.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  @NonNull
  private final JwtTokenProviderImpl jwtTokenProviderImpl;

  @NonNull
  private final FilterChainExceptionHandler filterChainExceptionHandler;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      @NonNull AuthenticationConfiguration authConfig)
      throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(@NonNull HttpSecurity http) throws Exception {
    http
        .httpBasic().disable()
        .csrf().disable()
        .authorizeRequests()
        .mvcMatchers("/api/**", Web.loginPath)
        .permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilterBefore(filterChainExceptionHandler, LogoutFilter.class)
        .apply(new JwtConfigurer(jwtTokenProviderImpl))
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    return http.build();
  }
}
