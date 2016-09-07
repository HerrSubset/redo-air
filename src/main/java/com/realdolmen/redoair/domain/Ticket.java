package com.realdolmen.redoair.domain;

import javax.persistence.*;

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
    private Long id;
    private String passengerFirstName;
    private String passengerLastName;

    @ManyToOne
    @JoinColumn(name = "category_fk")
    private Category seatCategory;

    @ManyToOne
    @JoinColumn(name = "booking_fk")
    private Booking booking;



    /***********************************************************
     * Constructors
     ***********************************************************/

    public Ticket() {

    }

    public Ticket(String passengerFirstName, String passengerLastName, Category seatCategory, Booking b) {
        this.passengerFirstName = passengerFirstName;
        this.passengerLastName = passengerLastName;
        this.seatCategory = seatCategory;
        this.booking = b;
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

    public String getFullName() {
        return this.passengerFirstName + " " + this.passengerLastName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getSeatCategory() {
        return seatCategory;
    }

    public void setSeatCategory(Category seatCategory) {
        this.seatCategory = seatCategory;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
