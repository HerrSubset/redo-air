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

    public List<Category> getAllFlights() {
        return cs.getAllFlights();
    }
}
