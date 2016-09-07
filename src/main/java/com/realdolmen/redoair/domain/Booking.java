package com.realdolmen.redoair.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Booking class
 *
 * Represents a collection of tickets that were bought simultaneously.
 * It also holds information on how the order was paid for.
 */
@Entity
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
