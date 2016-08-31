package com.realdolmen.redoair.domain;

import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by PSTBB36 on 31/08/2016.
 */
public class AirportTest extends JpaPersistenceTest {
    private Airport a;
    private final static Long TEST_REGION_ID = 5000L;
    private Region r;

    @Before
    public void setUp() {
        r = entityManager().find(Region.class, TEST_REGION_ID);
        a = new Airport(r, "AMS");
    }

    @Test
    public void shouldSaveAirportToDB() {
        assertNull(a.getId());
        entityManager().persist(a);
        assertNotNull(a.getId());
    }
}
