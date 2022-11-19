package com.team.passengerrailwaytransportation.config.security.jwt;

import com.team.passengerrailwaytransportation.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

  @NonNull
  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(@NonNull final String email) {
    final var user = userService.getUserByEmail(email);

    if (user == null) {
      throw new UsernameNotFoundException(
          String.format("User with email: %s not found", email));
    }

    return UserJwtFactory.createUser(user);
  }
}
