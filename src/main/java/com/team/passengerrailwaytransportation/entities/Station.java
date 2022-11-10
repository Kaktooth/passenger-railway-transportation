package com.team.passengerrailwaytransportation.entities;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;

@Getter
@ToString
@Entity
@Table(name = "stations")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Station extends Domain {

  String name;
  String location;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Station station = (Station) o;
    return getId() != null && Objects.equals(getId(), station.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
