package com.realdolmen.redoair.domain__;

import com.realdolmen.redoair.domain.Airport;
import com.realdolmen.redoair.domain.Flight;
import com.realdolmen.redoair.domain.NameContainer;
import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class NameContainerTest extends JpaPersistenceTest {
    private Flight f;
    private static final Long TEST_ARRIVAL_AIRPORT_ID = 5000L;
    private static final Long TEST_DEPARTURE_AIRPORT_ID = 5001L;
    private Airport departureAirport;
    private NameContainer nameContainer;

    @Before
    public void setUp() {
        nameContainer = new NameContainer();
        nameContainer.setLastName("Hanot");
        nameContainer.setFirstName("Nick");
    }

    @Test
    public void shouldReturnFullName() {
        nameContainer.getFullName();
        assertEquals(nameContainer.getFullName(), "Nick Hanot");
    }
}
