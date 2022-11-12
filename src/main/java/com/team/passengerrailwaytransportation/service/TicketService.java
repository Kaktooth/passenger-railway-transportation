package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Ticket;
import com.team.passengerrailwaytransportation.repository.TicketRepository;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TicketService extends AbstractService<Ticket, TicketRepository> {

  public TicketService(TicketRepository repository) {
    super(repository);
  }

  public List<Ticket> getUsersTickets(UUID userId) {
    return repository.findAllByUserId(userId);
  }
}
