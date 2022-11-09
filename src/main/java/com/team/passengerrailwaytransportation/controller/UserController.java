package com.team.passengerrailwaytransportation.controller;

import com.team.passengerrailwaytransportation.entities.User;
import com.team.passengerrailwaytransportation.repository.UserRepository;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserRepository repository;

  public UserController(UserRepository repository) {
    this.repository = repository;
  }

  @PostMapping
  public ResponseEntity<User> create(@Valid @RequestBody User resource) {
    User result = repository.save(resource);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }
}
