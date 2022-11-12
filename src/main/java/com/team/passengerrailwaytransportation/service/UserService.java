package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.User;
import com.team.passengerrailwaytransportation.repository.UserRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService extends AbstractService<User, UserRepository> {

  public UserService(UserRepository repository) {
    super(repository);
  }
}
