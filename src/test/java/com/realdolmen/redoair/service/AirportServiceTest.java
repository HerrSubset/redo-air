package com.realdolmen.redoair.service;

import com.realdolmen.redoair.domain.Airport;
import com.realdolmen.redoair.repository.AirportRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AirportServiceTest {
    @InjectMocks
    private AirportService service;

    @Mock
    private AirportRepository repo;
    private List<Airport> airports;

    @Before
    public void setUp() {
        airports = new ArrayList<>();
    }

    @Test
    public void getAllAirportsReturnsListReturnedByRepo() {
        Mockito.when(repo.findAll()).thenReturn(airports);
        Assert.assertSame(airports, service.getAllAirports());
    }
}
