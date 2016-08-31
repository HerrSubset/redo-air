package com.realdolmen.redoair.domain;

import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;


public class FlightTest extends JpaPersistenceTest {
    private Flight f;
    private static final Long TEST_ARRIVAL_AIRPORT_ID = 5000L;
    private Airport arrivalAirport;
    private static final Long TEST_DEPARTURE_AIRPORT_ID = 5001L;
    private Airport departureAirport;

    @Before
    public void setUp() {
        arrivalAirport = entityManager().find(Airport.class, TEST_ARRIVAL_AIRPORT_ID);
        departureAirport = entityManager().find(Airport.class, TEST_DEPARTURE_AIRPORT_ID);
        f = new Flight(departureAirport, arrivalAirport, new Date(), new Date());
    }


    @Test
    public void shouldSaveFlightToDB() {
        assertNull(f.getId());
        entityManager().persist(f);
        assertNotNull(f.getId());
    }
}
