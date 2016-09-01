package com.realdolmen.redoair.service;


import com.realdolmen.redoair.domain.Airport;
import com.realdolmen.redoair.repository.AirportRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@LocalBean
@Stateless
public class AirportService {
    @Inject
    AirportRepository repo;

    public List<Airport> getAllAirports() {
        return repo.findAll();
    }
}
