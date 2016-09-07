package com.realdolmen.redoair.service;


import com.realdolmen.redoair.domain.*;
import com.realdolmen.redoair.repository.BookingRepository;
import com.realdolmen.redoair.repository.TicketRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class BookingService implements Serializable{
    @Inject
    private PaymentService ps;

    @Inject
    private BookingRepository bookingRepo;

    @Inject
    private TicketRepository ticketRepo;

    public Booking createBooking(List<NameContainer> passengers, Category departureFlight, Category returnFlight, Payment p) {
        Booking b = new Booking(p);
        List<Ticket> tickets = createTicketList(b, passengers, departureFlight);

        if (returnFlight != null)
            tickets.addAll(createTicketList(b, passengers, returnFlight));


        bookingRepo.create(b);

        for (Ticket t: tickets) {
            ticketRepo.create(t);
        }

        return b;
    }

    private List<Ticket> createTicketList(Booking b, List<NameContainer> passengers, Category category) {
        List<Ticket> tickets = new ArrayList<>();

        for (NameContainer p : passengers) {
            Ticket t = new Ticket(p.getFirstName(), p.getLastName(), category, b);
            tickets.add(t);
        }

        return tickets;
    }
}
