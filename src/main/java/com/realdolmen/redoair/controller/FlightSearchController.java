package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.Category;
import com.realdolmen.redoair.domain.FlightCombo;
import com.realdolmen.redoair.service.CategoryService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class FlightSearchController implements Serializable {
    @Inject
    private CategoryService cs;

    private String departureAirport;
    private String arrivalAirport;
    private Date departureDate;
    private Date returnDate;
    private String className;
    private Integer numberOfPeople;
    private String airline;

    /**
     * returns the flights from the database, filtered with
     * the parameters given to the controller.
     */
    public List<Category> getDepartureFlights() {
        return cs.getFilteredFlights(departureAirport, arrivalAirport, departureDate, className, numberOfPeople, airline);
    }

    public List<Category> getReturnFlights() {
        return cs.getFilteredFlights(arrivalAirport, departureAirport, returnDate, className, numberOfPeople, airline);
    }

    public List<FlightCombo> getFlightCombos() {
        return cs.getFlightCombos(departureAirport, arrivalAirport, departureDate, returnDate, className, numberOfPeople, airline);
    }

    public boolean shouldShowReturnFlights() {
        return returnDate != null;
    }


    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        if (!departureAirport.equals("")) {
            this.departureAirport = departureAirport;
        }
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        if (!arrivalAirport.equals("")) {
            this.arrivalAirport = arrivalAirport;
        }
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        if (!className.equals("")) {
            this.className = className;
        }
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        if (!airline.equals("")) {
            this.airline = airline;
        }
    }

    public boolean flightsWereFound() {
        boolean res = true;
        List<FlightCombo> flightCombos = getFlightCombos();

        if (flightCombos == null)
            res = false;
        else if (flightCombos.size() < 1)
            res = false;

        return res;
    }
}
