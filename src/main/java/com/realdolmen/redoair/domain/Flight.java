package com.realdolmen.redoair.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Flight class
 *
 * Contains information on a flight operated by a partner airline.
 * It has a destination
 */
@Entity
public class Flight {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departure_airport_fk")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_fk")
    private Airport arrivalAirport;

    private Date departureTime;
    private Date arrivalTime;



    /***********************************************************
     * Constructors
     ***********************************************************/
    public Flight() {

    }

    public Flight(Airport departureAirport, Airport arrivalAirport, Date departureTime, Date arrivalTime) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
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

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
