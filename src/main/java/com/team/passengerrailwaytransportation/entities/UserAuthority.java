package com.team.passengerrailwaytransportation.entities;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorities")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAuthority extends Domain {

  @Column(columnDefinition = "user_id")
  UUID userId;

  @Column(columnDefinition = "authority_id")
  Integer authorityId;
}
