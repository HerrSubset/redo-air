package com.realdolmen.redoair.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Category class.
 *
 * A category belongs to a flight. It's a collection of seats that have the
 * same pricing. Examples are economy seats or business class seats.
 *
 * The partner will offer us a certain amount of seats we can sell, this info
 * is stored in this class.
 */
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_fk")
    private Flight flight;

    @OneToMany(mappedBy = "seatCategory", fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    private String name;

    private int maxNumberOfSeats;
    private double basePrice;
    private double baseMarkupPercentage;

    /***********************************************************
     * Constructors
     ***********************************************************/
    public Category() {

    }

    public Category(int maxNumberOfSeats, double basePrice, double baseMarkupPercentage) {
        this.maxNumberOfSeats = maxNumberOfSeats;
        this.basePrice = basePrice;
        this.baseMarkupPercentage = baseMarkupPercentage;
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

    public int getMaxNumberOfSeats() {
        return maxNumberOfSeats;
    }

    public void setMaxNumberOfSeats(int maxNumberOfSeats) {
        this.maxNumberOfSeats = maxNumberOfSeats;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getBaseMarkupPercentage() {
        return baseMarkupPercentage;
    }

    public void setBaseMarkupPercentage(double baseMarkupPercentage) {
        this.baseMarkupPercentage = baseMarkupPercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Double getPrice() {
        return basePrice + (basePrice * baseMarkupPercentage);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getFreeSeats() {
        return maxNumberOfSeats - tickets.size();
    }

    public Double getFinalPrice() {
        return basePrice + (basePrice * baseMarkupPercentage);
    }
}
