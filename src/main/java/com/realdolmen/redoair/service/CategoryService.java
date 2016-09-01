package com.realdolmen.redoair.service;

import com.realdolmen.redoair.domain.Category;
import com.realdolmen.redoair.repository.CategoryRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
public class CategoryService {
    @Inject
    CategoryRepository repo;

    public List<String> getAllCategoryNames() {
        return repo.findAllNames();
    }

    public List<Category> getAllFlights() {
        return repo.findAll();
    }

    public List<Category> getFilteredFlights(String departureAirport, String arrivalAirport, Date departureDate,
                                             Date returnDate, String className, Integer numberOfPeople,
                                             String airline) {
        return repo.getFilteredFlights(departureAirport, arrivalAirport, departureDate, returnDate, className,
                                        numberOfPeople, airline);

    }
}
