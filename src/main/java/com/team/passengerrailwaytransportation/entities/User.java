package com.team.passengerrailwaytransportation.entities;

import com.team.passengerrailwaytransportation.annotation.EmailValidation;
import com.team.passengerrailwaytransportation.annotation.PasswordValidation;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;

@Getter
@Setter
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends Domain {

  @EmailValidation
  @NotEmpty(message = "Email should not be empty")
  String email;

  @PasswordValidation
  @NotEmpty(message = "Password should not be empty")
  String password;

  @NotEmpty(message = "Name should not be empty")
  String name;

  @NotEmpty(message = "SurName should not be empty")
  String surname;

  @NotEmpty(message = "Patronymic should not be empty")
  String patronymic;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    User user = (User) o;
    return getId() != null && Objects.equals(getId(), user.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
