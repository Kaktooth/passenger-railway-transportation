package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.User;
import com.team.passengerrailwaytransportation.entities.UserAuthority;
import com.team.passengerrailwaytransportation.exeption.UserNotFoundException;
import com.team.passengerrailwaytransportation.repository.UserAuthorityRepository;
import com.team.passengerrailwaytransportation.repository.UserRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService extends AbstractService<User, UserRepository> {

  @Autowired
  private UserAuthorityRepository authorityRepository;

  public UserService(UserRepository repository) {
    super(repository);
  }

  public User getUserByEmail(String email) {
    return repository.findUserByEmail(email)
        .orElseThrow(() -> new UserNotFoundException(email));
  }

  @Override
  public User save(User user) {
    User simpleUser = repository.save(user);
    authorityRepository.save(new UserAuthority(user.getId(), 0));

    return simpleUser;
  }
}
