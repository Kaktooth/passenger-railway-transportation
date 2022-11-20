package com.team.passengerrailwaytransportation.entities;

import com.team.passengerrailwaytransportation.annotation.EmailValidation;
import com.team.passengerrailwaytransportation.annotation.PasswordValidation;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Formula;

@Getter
@Setter
@Entity
@ToString
@Table(name = "users")
@AllArgsConstructor
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

  @NotEmpty(message = "Surname should not be empty")
  String surname;

  @NotEmpty(message = "Patronymic should not be empty")
  String patronymic;

  @Formula(" ( SELECT ua.authorities FROM users JOIN public.authorities on users.id = authorities.user_id JOIN user_authorities ua on authorities.authority_id = ua.id) ")
  @Enumerated(EnumType.STRING)
  Role role;

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
