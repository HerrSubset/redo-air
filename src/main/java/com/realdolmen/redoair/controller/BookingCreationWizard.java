package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.Category;
import com.realdolmen.redoair.domain.NameContainer;
import com.realdolmen.redoair.service.CategoryService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@ConversationScoped
@Named
public class BookingCreationWizard implements Serializable {
    @Inject
    private Conversation conversation;

    @Inject
    private CategoryService service;

    @Inject
    SessionController sessionController;


    private Long departureId;
    private Long returnId;
    private Category departureFlight;
    private Category returnFlight;
    private int numberOfPeople;
    private List<NameContainer> passengerlist;

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

//        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect("../payment.jsf");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return "/payment.xhtml";
    }

    public void test() {
        for (NameContainer n: this.passengerlist) {
            System.out.println("Name: " + n.getFullName());
        }
    }
}
