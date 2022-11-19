package com.team.passengerrailwaytransportation.repository;

import com.team.passengerrailwaytransportation.entities.User;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CommonRepository<User> {

  Optional<User> findUserByEmail(String email);
}
