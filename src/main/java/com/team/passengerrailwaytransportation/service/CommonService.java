package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Domain;
import java.util.List;
import java.util.UUID;

public interface CommonService<E extends Domain> {

  E save(E e);

  E update(E e);

  E findById(UUID id);

  E delete(UUID id);

  List<E> findAll();
}