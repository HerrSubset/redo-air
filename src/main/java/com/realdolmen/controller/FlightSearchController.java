package com.realdolmen.controller;

import com.realdolmen.redoair.domain.Category;
import com.realdolmen.redoair.service.CategoryService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class FlightSearchController implements Serializable {
    @Inject
    private CategoryService cs;

    private String departureAirport;
    private String arrivalAirport;
    private String departureDate;
    private String arrivalDate;
    private String className;
    private Integer numberOfPeople;
    private String airline;

    /**
     * returns the flights from the database, filtered with
     * the parameters given to the controller.
     */
    public List<Category> getFlights() {
        return cs.getFilteredFlights(departureAirport, arrivalAirport, departureDate, arrivalDate, className, numberOfPeople, airline);
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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        if (!departureDate.equals("")) {
            this.departureDate = departureDate;
        }
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        if (!arrivalDate.equals("")) {
            this.arrivalDate = arrivalDate;
        }
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
}
