package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.Category;
import com.realdolmen.redoair.service.CategoryService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ConversationScoped
@Named
public class BookingCreationWizard implements Serializable {
    @Inject
    private Conversation conversation;

    @Inject
    private CategoryService service;


    private Long departureId;
    private Long returnId;
    private Category departureFlight;
    private Category returnFlight;
    private int numberOfPeople;

    @PostConstruct
    public void init() {
        this.numberOfPeople = 1;
    }

    public int getNumberOfPeople() {
        System.out.println("Entered bookingCreationWizard's constructor");
        return this.numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (conversation.isTransient()){
            conversation.begin();
            System.out.println("Conversation started");
        }
        this.numberOfPeople = numberOfPeople;
        System.out.println("Changed number of people to " + this.numberOfPeople);
    }

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
        if (departureFlight == null) {
            this.departureFlight = service.getFlightById(this.departureId);
        }
        return this.departureFlight;
    }

    public Category getReturnFlight() {
        if (returnFlight == null) {
            this.returnFlight = service.getFlightById(this.returnId);
        }
        return this.returnFlight;
    }

    public boolean hasReturnFlight() {
        return this.returnId != null;
    }

    public String chooseFlight() {
        return "details.jsf?faces-redirect=true";
    }
}
