package com.realdolmen.redoair.domain;

import com.realdolmen.redoair.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Flight class
 *
 * Contains information on a flight operated by a partner airline.
 * It has a destination
 */
@Entity
public class Flight extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "departure_airport_fk")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_fk")
    private Airport arrivalAirport;

    @ManyToOne
    @JoinColumn(name = "partner_fk")
    private Partner airline;

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

    public Partner getAirline() {
        return airline;
    }

    public void setAirline(Partner airline) {
        this.airline = airline;
    }
}
