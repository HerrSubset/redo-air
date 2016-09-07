package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.*;
import com.realdolmen.redoair.service.BookingService;
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
    private BookingService bookingService;

    @Inject
    private CategoryService categoryService;


    private Long departureId;
    private Long returnId;
    private Category departureFlight;
    private Category returnFlight;
    private int numberOfPeople;
    private List<NameContainer> passengerlist;
    private Long number;

    @PostConstruct
    public void init() {
        this.fillPassengerList();
    }

    private void fillPassengerList() {
        this.passengerlist = new ArrayList<>();

        for (int i = 0; i < numberOfPeople; i ++ ) {
            passengerlist.add(new NameContainer());
        }
    }

    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (conversation.isTransient()){
            conversation.begin();
        }
        fillPassengerList();    // initialize list with the correct number of empty strings
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
            this.departureFlight = categoryService.getFlightById(this.departureId);
        }
        return this.departureFlight;
    }

    public Category getReturnFlight() {
        if (returnFlight == null) {
            this.returnFlight = categoryService.getFlightById(this.returnId);
        }
        return this.returnFlight;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public boolean hasReturnFlight() {
        return this.returnId != null;
    }

    public List<NameContainer> getPassengerlist() {
        return passengerlist;
    }

    public void setPassengerlist(List<NameContainer> passengerlist) {
        this.passengerlist = passengerlist;
    }

    public String chooseFlight() {
        return "details.jsf?faces-redirect=true";
    }

    public String proceedToPayment() {
        return "payment";
    }

    public void createBooking() {
        bookingService.createBooking(passengerlist, departureFlight, returnFlight, new Payment(PaymentType.CREDITCARD, new CreditCard(number)));
        if (!conversation.isTransient()){
            conversation.end();
        }
    }
}
