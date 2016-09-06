package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.service.AirportService;
import com.realdolmen.redoair.service.CategoryService;
import com.realdolmen.redoair.service.PartnerService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

@RequestScoped
@ManagedBean
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

    private Date returnDate;
    private Date currentDate;
    private Date departureDate;

    @PostConstruct
    public void setUp() {
        numberOfPersons=1;
        departureAirport="";
        destinationAirport="";
        departureDay="";
        returnDay="";
        category="";
        airline="";
        currentDate = new Date();
        departureDate = null;
        oneWay=false;
    }

    public AirportService getAirportService() {
        return airportService;
    }

    public void setAirportService(AirportService airportService) {
        System.out.println(airportService);
        this.airportService = airportService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        System.out.println(categoryService);
        this.categoryService = categoryService;
    }

    public PartnerService getPartnerService() {
        return partnerService;
    }

    public void setPartnerService(PartnerService partnerService) {
        System.out.println(partnerService);
        this.partnerService = partnerService;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        System.out.println(departureAirport);
        this.departureAirport = departureAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        System.out.println(destinationAirport);
        this.destinationAirport = destinationAirport;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        System.out.println(category);
        this.category = category;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        System.out.println(numberOfPersons);
        this.numberOfPersons = numberOfPersons;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        System.out.println(airline);
        this.airline = airline;
    }

    public boolean getOneWay() {
        return oneWay;
    }

    public void setOneWay(boolean oneWay) {
        System.out.println(oneWay);
        this.oneWay = oneWay;
    }

    public String doAction() {
        String s = "/flights/search.jsf";
        s = s +"?numberofpeople=" + numberOfPersons;

        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            departureDay = df.format(departureDate);
            if(!departureDay.isEmpty()) {
                s = s +"&departuredate=" + departureDay;
            }
        } catch(NullPointerException e) {
//            e.printStackTrace();
            // TODO: 1/09/2016
        }

        try {
            if (!destinationAirport.isEmpty()) {
                String ss = destinationAirport.substring(0,3);
                s = s + "&arrivalAirport=" + ss;
            }
        } catch(NullPointerException e) {
            // TODO: 1/09/2016
//            e.printStackTrace();
        }

        try {
            if(!departureAirport.isEmpty()) {
                String ss = departureAirport.substring(0,3);
            s = s +"&departureAirport=" + ss;
            }
        } catch(NullPointerException e) {
            // TODO: 1/09/2016
//            e.printStackTrace();
        }

        try {
            if(!category.isEmpty()) {
                s = s +"&class=" + category;
            }
        } catch(NullPointerException e) {
            // TODO: 1/09/2016
//            e.printStackTrace();
        }

        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            returnDay = df.format(returnDate);
            if(!returnDay.isEmpty()) {
                s = s +"&returndate=" + returnDay;
            }
        } catch(NullPointerException e) {
            // TODO: 1/09/2016
//            e.printStackTrace();
        }

        try {
            if(!airline.isEmpty()) {
                s = s +"&airline=" + airline;
            }
        } catch(NullPointerException e) {
            // TODO: 1/09/2016
//            e.printStackTrace();
        }

        return s+"faces-redirect=true";
    }

    public Date getReturndate() {
        return returnDate;
    }

    public void setReturndate(Date returndate) {
        this.returnDate = returndate;
    }

    public Date getDeparturedate() {
        return departureDate;
    }

    public void setDeparturedate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

}