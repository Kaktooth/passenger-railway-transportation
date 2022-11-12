package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Domain;
import com.team.passengerrailwaytransportation.repository.CommonRepository;
import java.util.List;
import java.util.UUID;

public class AbstractService<E extends Domain, R extends CommonRepository<E>> implements
    CommonService<E> {

  protected R repository;

  public AbstractService(R repository) {
    this.repository = repository;
  }

  @Override
  public E save(E e) {
    return repository.save(e);
  }

  @Override
  public E update(E e) {
    return repository.save(e);
  }

  @Override
  public E findById(UUID id) {
    return repository.findById(id).get();
  }

  @Override
  public E delete(UUID id) {
    E e = findById(id);
    repository.delete(e);
    return e;
  }

  @Override
  public List<E> findAll() {
    return repository.findAll();
  }
}
