package com.team.passengerrailwaytransportation.repository;

import com.team.passengerrailwaytransportation.entities.Domain;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface CommonRepository<E extends Domain> extends JpaRepository<E, UUID>,
    PagingAndSortingRepository<E, UUID>,
    JpaSpecificationExecutor<E> {

}
