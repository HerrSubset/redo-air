package com.realdolmen.redoair.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Ticket class
 *
 * A ticket is the link between a customer that bought a seat on a flight.
 * It is always for a one-way flight, when booking a 2-way flight, you get
 * two tickets. Tickets are not necessarily on the name of the person that
 * bought them.
 */
@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private long id;
    private String passengerFirstName;
    private String passengerLastName;
    private Category seatCategory;



    /***********************************************************
     * Constructors
     ***********************************************************/

    public Ticket() {

    }

    public Ticket(String passengerFirstName, String passengerLastName, Category seatCategory) {
        this.passengerFirstName = passengerFirstName;
        this.passengerLastName = passengerLastName;
        this.seatCategory = seatCategory;
    }



    /***********************************************************
     * Getters / Setters
     ***********************************************************/

    public String getPassengerFirstName() {
        return passengerFirstName;
    }

    public void setPassengerFirstName(String passengerFirstName) {
        this.passengerFirstName = passengerFirstName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getSeatCategory() {
        return seatCategory;
    }

    public void setSeatCategory(Category seatCategory) {
        this.seatCategory = seatCategory;
    }
}
