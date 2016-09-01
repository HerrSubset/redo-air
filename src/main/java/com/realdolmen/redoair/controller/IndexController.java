package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.Airport;
import com.realdolmen.redoair.service.AirportService;
import com.realdolmen.redoair.service.CategoryService;
import com.realdolmen.redoair.service.PartnerService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by NHOBB65 on 1/09/2016.
 */
@ManagedBean
@RequestScoped
public class IndexController {
    @Inject
    AirportService airportService;

    @Inject
    CategoryService categoryService;

    @Inject
    PartnerService partnerService;


    private String departureAirport;
    private String destinationAirport;
    private String departureDay;
    private String returnDay;
    private String category;
    @NotNull
    @Min(1)
    private int numberOfPersons;
    private String airline;
    private boolean oneWay;

    @PostConstruct
    public void setUp() {
        numberOfPersons=1;
        departureAirport="";
        destinationAirport="";
        departureDay="";
        returnDay="";
        category="";
        airline="";
    }

    public AirportService getAirportService() {
        return airportService;
    }

    public void setAirportService(AirportService airportService) {
        this.airportService = airportService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public PartnerService getPartnerService() {
        return partnerService;
    }

    public void setPartnerService(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(String departureDay) {
        this.departureDay = departureDay;
    }

    public String getReturnDay() {
        return returnDay;
    }

    public void setReturnDay(String returnDay) {
        this.returnDay = returnDay;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public boolean isOneWay() {
        return oneWay;
    }

    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;
    }

    public String doAction() {
        String s = "flights/list.jsf";

        s = s +"?numberOfPersons=" + numberOfPersons;

        if(!departureDay.isEmpty()) {
            s = s +"&departureday=" + departureDay;
        }
        
        try {
            if (!destinationAirport.isEmpty()) {
                s = s + "&destinationAirport=" + destinationAirport.substring(0,3);
            }
        } catch(NullPointerException e) {
            // TODO: 1/09/2016
        }

        try {
            if(!departureAirport.isEmpty()) {
            s = s +"&departureAirport=" + departureAirport.substring(0,3);
            }
        } catch(NullPointerException e) {
            // TODO: 1/09/2016
        }

        try {
            if(!category.isEmpty()) {
                s = s +"&category=" + category;
            }
        } catch(NullPointerException e) {
            // TODO: 1/09/2016
        }

        try {
            if(!returnDay.isEmpty()) {
                s = s +"&returnDay=" + returnDay;
            }
        } catch(NullPointerException e) {
            // TODO: 1/09/2016
        }

        try {
            if(!airline.isEmpty()) {
                s = s +"&airline=" + airline;
            }
        } catch(NullPointerException e) {
            // TODO: 1/09/2016
        }

        System.out.println("*******************");
        System.out.println(s);

        return s;
    }
}
