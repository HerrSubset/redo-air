package com.realdolmen.redoair.repository;


import com.realdolmen.redoair.domain.Airport;
import com.realdolmen.redoair.domain.Region;
import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

public class AirportRepositoryTest extends JpaPersistenceTest {
    private AirportRepository repo;
    private Region r;
    private static final Long TEST_AIRPORT_ID = 5004L;
    private static final Long TEST_REGION_ID = 5000L;

    @Before
    public void setUp() {
        repo = new AirportRepository();
        repo.em = entityManager();
        r = entityManager().find(Region.class, TEST_REGION_ID);
    }

    @Test
    public void createShouldMakeNewAirport() {
        Airport a = new Airport(r, "FRA");
        repo.create(a);
        assertNotNull(a.getId());
    }

    @Test
    public void updateShouldChangeAirport() {
        Airport a = entityManager().find(Airport.class, TEST_AIRPORT_ID);
        a.setCode("mun");
        repo.update(a);
        a = entityManager().find(Airport.class, TEST_AIRPORT_ID);
        assertEquals("mun", a.getCode());
    }

    @Test
    public void deleteByObjectShouldRemoveAirport() {
        Airport a = entityManager().find(Airport.class, TEST_AIRPORT_ID);
        repo.delete(a);
        assertNull(entityManager().find(Airport.class, TEST_AIRPORT_ID));
    }

    @Test
    public void deleteByIdShouldRemoveAirport() {
        repo.delete(TEST_AIRPORT_ID);
        assertNull(entityManager().find(Airport.class, TEST_AIRPORT_ID));
    }

    @Test
    public void findByIdReturnsCorrectAirport() {
        assertSame(entityManager().find(Airport.class, TEST_AIRPORT_ID), repo.findById(TEST_AIRPORT_ID));
    }

    @Test
    public void invalidIdReturnsNull() {
        assertNull(repo.findById(9000L));
    }

    @Test
    public void findAllReturnsCorrectNumberOfAirports() {
        assertEquals(5, repo.findAll().size());
    }
}
