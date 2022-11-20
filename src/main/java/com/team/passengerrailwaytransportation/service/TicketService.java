package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Ticket;
import com.team.passengerrailwaytransportation.exeption.BigSeatNumberException;
import com.team.passengerrailwaytransportation.repository.TicketRepository;
import com.team.passengerrailwaytransportation.repository.TrainRepository;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TicketService extends AbstractService<Ticket, TicketRepository> {

  @Autowired
  TrainRepository trainRepository;

  public TicketService(TicketRepository repository) {
    super(repository);
  }

  @Override
  public Ticket save(Ticket ticket) {
    if (ticket.getPlaceNumber() <= trainRepository.getSeatsNumberByTrainId(
        ticket.getTransportation().getTrain().getId())) {
      return super.save(ticket);
    } else {
      throw new BigSeatNumberException("Seat number bigger than train seats!");
    }
  }

  public List<Ticket> getUsersTickets(UUID userId) {
    return repository.findAllByUserId(userId);
  }
}
