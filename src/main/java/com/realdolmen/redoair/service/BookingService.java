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

    public Booking createBooking(List<NameContainer> passengers, Category category, Payment p) {

        List<Ticket> tickets = new ArrayList<>();

        Booking b = new Booking(p);
        for (NameContainer passenger: passengers) {
            Ticket t = new Ticket(passenger.getFirstName(), passenger.getLastName(), category, b);
            tickets.add(t);
        }


        bookingRepo.create(b);

        for (Ticket t: tickets) {
            ticketRepo.create(t);
        }

        return b;
    }
}
