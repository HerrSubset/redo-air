package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.service.AirportService;
import com.realdolmen.redoair.service.CategoryService;
import com.realdolmen.redoair.service.PartnerService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

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
}
