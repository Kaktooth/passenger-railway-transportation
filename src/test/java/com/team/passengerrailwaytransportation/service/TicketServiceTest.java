package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.Ticket;
import com.team.passengerrailwaytransportation.entities.Train;
import com.team.passengerrailwaytransportation.entities.Transportation;
import com.team.passengerrailwaytransportation.entities.Wagon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class TicketServiceTest {
    @Autowired
    private TicketService ticketService;
    private TransportationService transportationService;

    @Test
    public void shouldReturnCorrectAllTicket() {
        Transportation transportation = createTrain();
        Ticket ticket = createTicket(transportation);
        List<Ticket> list = ticketService.getUsersTickets(UUID.randomUUID());
        Assertions.assertTrue(list.contains(ticket));
    }

    private Ticket createTicket(Transportation transportation) {
        Ticket ticket = new Ticket(new Wagon(UUID.randomUUID(), UUID.randomUUID()),
                UUID.randomUUID(), 1, transportation, 199);
        return ticketService.save(ticket);
    }

    private Transportation createTrain() {
        Transportation transportation = new Transportation();
        return transportationService.save(transportation);
    }
}
