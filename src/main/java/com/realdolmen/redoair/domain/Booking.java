package com.realdolmen.redoair.domain;

import com.realdolmen.redoair.AbstractEntity;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Booking class
 *
 * Represents a collection of tickets that were bought simultaneously.
 * It also holds information on how the order was paid for.
 */
@Entity
public class Booking extends AbstractEntity {
    @Embedded
    @Valid
    private Payment payment;

    @OneToMany(mappedBy = "booking", fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    /***********************************************************
     * Constructors
     ***********************************************************/
    public Booking() {

    }

    public Booking(Payment p) {
        this.payment = p;
    }

    public Booking(Payment p, List<Ticket> tickets) {
        this.payment = p;
        this.tickets = tickets;
    }


    /***********************************************************
     * Getters / Setters
     ***********************************************************/
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
