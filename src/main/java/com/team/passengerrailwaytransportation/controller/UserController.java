package com.team.passengerrailwaytransportation.controller;

import com.team.passengerrailwaytransportation.config.security.jwt.JwtTokenProviderImpl;
import com.team.passengerrailwaytransportation.entities.AuthenticationUserDto;
import com.team.passengerrailwaytransportation.entities.Role;
import com.team.passengerrailwaytransportation.entities.User;
import com.team.passengerrailwaytransportation.service.UserService;
import com.team.passengerrailwaytransportation.utility.AppConstraints;
import java.util.Map;
import java.util.Set;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

  @NonNull
  private final AuthenticationManager authenticationManager;

  @NonNull
  private final JwtTokenProviderImpl jwtTokenProviderImpl;

  @NonNull
  private final UserService userService;

  @NonNull
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/register")
  public ResponseEntity<String> register(@Valid @RequestBody @NonNull User user) {
    try {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      String encodedPassword = passwordEncoder.encode(user.getPassword());
      User newUser = new User(user.getEmail(), encodedPassword, user.getName(), user.getSurname(),
          user.getPatronymic(), Role.USER);
      log.info(newUser.toString());
      User registeredUser = userService.save(newUser);

      return ResponseEntity.status(201)
          .body("Created new user with email " + registeredUser.getEmail());
    } catch (RuntimeException ex) {
      log.info(ex.getMessage());
      return ResponseEntity.status(500)
          .body(ex.getMessage());
    }

  }

  @PostMapping("/login")
  public ResponseEntity<Map<Object, Object>> login(
      @Valid @RequestBody @NonNull AuthenticationUserDto request) {
    final var email = request.getEmail();
    final var password = request.getPassword();

    try {
      var auth = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(email, password));

      log.info("auth" + auth.toString());

      final var user = userService.getUserByEmail(email);
log.info(user.toString());
      if (user == null) {
        throw new UsernameNotFoundException(
            String.format("User with email: %s not found", email));
      }

      final var token = AppConstraints.Web.Security.tokenPrefix
          + jwtTokenProviderImpl.createToken(email, Set.of(user.getRole(), Role.USER));

      final Map<Object, Object> response = Map.of("email", email, "token", token);

      return new ResponseEntity<>(response, HttpStatus.CREATED);
    } catch (AuthenticationException e) {
      log.info("Invalid username or password");
      throw new BadCredentialsException("Invalid username or password");
    }
  }
}
