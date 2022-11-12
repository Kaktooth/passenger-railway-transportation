package com.team.passengerrailwaytransportation.repository;


import com.team.passengerrailwaytransportation.entities.Ticket;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CommonRepository<Ticket> {

  List<Ticket> findAllByUserId(UUID id);
}
