package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.Category;
import com.realdolmen.redoair.service.CategoryService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class FlightDetailsController {
    @Inject
    private CategoryService cs;

    private Long departureId;
    private Long returnId;
    private Category departureFlight;
    private Category returnFlight;

    public Long getDepartureId() {
        return departureId;
    }

    public void setDepartureId(Long departureId) {
        this.departureId = departureId;
    }

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public Category getDepartureFlight() {
        if (this.departureFlight == null) {
            this.departureFlight = cs.getFlightById(this.departureId);
        }
        return this.departureFlight;
    }

    public Category getReturnFlight() {
        if (this.returnFlight == null) {
            this.returnFlight = cs.getFlightById(this.departureId);
        }
        return this.returnFlight;
    }

    public boolean hasReturnFlight() {
        return this.returnId != null;
    }
}
