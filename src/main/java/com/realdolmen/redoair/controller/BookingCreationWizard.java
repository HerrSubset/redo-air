package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.Category;
import com.realdolmen.redoair.service.CategoryService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private List<String> passengerlist;

    @PostConstruct
    public void init() {
        this.numberOfPeople = 1;
        this.fillPassengerList();
    }

    private void fillPassengerList() {
        this.passengerlist = new ArrayList<>();

        for (int i = 0; i < numberOfPeople; i ++ ) {
            passengerlist.add("");
        }
    }

    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (conversation.isTransient()){
            conversation.begin();
        }
        this.numberOfPeople = numberOfPeople;
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

    public List<String> getPassengerlist() {
        if (passengerlist.size() != this.numberOfPeople) {
            this.fillPassengerList();
        }
        return passengerlist;
    }

    public void setPassengerlist(List<String> passengerlist) {
        this.passengerlist = passengerlist;
    }

    public String chooseFlight() {
        return "details.jsf?faces-redirect=true";
    }

    public String proceedToPayment() {
        System.out.print("Proceeding");
        return "payment";
    }

    public void test() {
        System.out.println("testing test method");
        for (String s: this.passengerlist) {
            System.out.println("Name: " + s);
        }
    }
}
