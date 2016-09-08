package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.*;
import com.realdolmen.redoair.service.BookingService;
import com.realdolmen.redoair.service.CategoryService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@ConversationScoped
@Named
public class BookingCreationWizard implements Serializable {
    @Inject
    private Conversation conversation;

    @Inject
    private BookingService bookingService;

    @Inject
    private CategoryService categoryService;

    @Inject
    SessionController sessionController;


    private Long departureId;
    private Long returnId;
    private Category departureFlight;
    private Category returnFlight;
    private int numberOfPeople;
    private List<NameContainer> passengerlist;
    private Long number;
    private PaymentType paymentType;

    /***********************************************************
     * Util methods
     ***********************************************************/

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

    public boolean hasReturnFlight() {
        return this.returnId != null;
    }


    /***********************************************************
     * Getters / Setters
     ***********************************************************/

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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public List<NameContainer> getPassengerlist() {
        return passengerlist;
    }

    public void setPassengerlist(List<NameContainer> passengerlist) {
        this.passengerlist = passengerlist;
    }


    /**
     * put all payment types in a SelectItem array so that they can be rendered easily by a radiobutton group
     */
    public SelectItem[] getPaymentOptions() {
        SelectItem[] items = new SelectItem[2];
        items[0] = new SelectItem(PaymentType.CREDITCARD, "Credit Card");
        items[1] = new SelectItem(PaymentType.ENDORSEMENT, "Endorsement");
        return items;
    }



    /***********************************************************
     * Redirection methods
     ***********************************************************/
    public String chooseFlight() {
        return "details.jsf?faces-redirect=true";
    }

    public String proceedToPayment() {

        return "/payment.xhtml";
    }

    public String createBooking() {
        Payment p = new Payment(paymentType);
        if ( number != null )
            p.setCreditCard(new CreditCard(number));


        Booking b = bookingService.createBooking(passengerlist, departureFlight, returnFlight, p);
        if (!conversation.isTransient()){
            conversation.end();
        }

        return "booking.jsf?bookingid=" + b.getId() + "&faces-redirect=true" ;
    }
}
