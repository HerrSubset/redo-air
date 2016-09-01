package com.realdolmen.redoair.repository;


import com.realdolmen.redoair.domain.Airport;
import com.realdolmen.redoair.domain.Flight;
import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class FlightRepositoryTest extends JpaPersistenceTest {
    private FlightRepository repo;
    private static final Long TEST_FLIGHT_ID = 5004L;
    private static final Long TEST_DEPART_AIRP_ID = 5000L;
    private static final Long TEST_ARRIV_AIRP_ID = 5003L;
    private Airport arriv;
    private Airport dep;

    @Before
    public void setUp() {
        repo = new FlightRepository();
        repo.em = entityManager();
        dep = entityManager().find(Airport.class, TEST_DEPART_AIRP_ID);
        arriv = entityManager().find(Airport.class, TEST_ARRIV_AIRP_ID);
    }

    @Test
    public void createShouldMakeNewFlight() {
        Flight f = new Flight(dep, arriv, new Date(), new Date());
        repo.create(f);
        assertNotNull(f.getId());
    }

    @Test
    public void updateShouldChangeFlight() {
        Flight f = entityManager().find(Flight.class, TEST_FLIGHT_ID);
        f.setArrivalAirport(arriv);
        f = repo.update(f);
        assertSame(f.getArrivalAirport(), arriv);
        f = entityManager().find(Flight.class, TEST_FLIGHT_ID);
        assertSame(f.getArrivalAirport(), arriv);
    }

    @Test
    public void deleteByIdShouldRemoveFlight() {
        repo.delete(TEST_FLIGHT_ID);
        assertNull(entityManager().find(Flight.class, TEST_FLIGHT_ID));
    }

    @Test
    public void deleteByObjectShouldRemoveFlight() {
        Flight f = entityManager().find(Flight.class, TEST_FLIGHT_ID);
        repo.delete(f);
        assertNull(entityManager().find(Flight.class, TEST_FLIGHT_ID));
    }

    @Test
    public void findByIdReturnsCorrectFlight() {
        Flight f = repo.findById(TEST_FLIGHT_ID);
        assertSame(f, entityManager().find(Flight.class, TEST_FLIGHT_ID));
    }

    @Test
    public void invalidIdReturnsNull() {
        assertNull(repo.findById(9000L));
    }

    @Test
    public void findAllReturnsAllFlights() {
        assertEquals(6, repo.findAll().size());
    }
}
