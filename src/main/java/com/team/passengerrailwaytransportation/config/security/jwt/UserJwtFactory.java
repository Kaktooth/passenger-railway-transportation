package com.team.passengerrailwaytransportation.config.security.jwt;


import com.team.passengerrailwaytransportation.entities.Role;
import com.team.passengerrailwaytransportation.entities.User;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserJwtFactory {

  public static UserJwt createUser(@NonNull final User user) {
    return new UserJwt(
        user.getId(),
        user.getEmail(),
        user.getPassword(),
        user.getName(),
        user.getSurname(),
        user.getPatronymic(),
        true,
        mapToGrantedAuthorities(user.getRole())
    );
  }

  private static List<GrantedAuthority> mapToGrantedAuthorities(
      @NonNull final Role userRole) {
    List<GrantedAuthority> authorityList = new ArrayList<>();
    authorityList.add(new SimpleGrantedAuthority(userRole.toString()));
    return authorityList;
  }
}
