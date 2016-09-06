package com.realdolmen.redoair.domain__;
import com.realdolmen.redoair.domain.*;


import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

public class RegionTest extends JpaPersistenceTest {
    private Region r;

    @Before
    public void setUp() {
        r = new Region("Western Europe");
    }

    @Test
    public void shouldSaveRegionToDB() {
        assertNull(r.getId());
        entityManager().persist(r);
        assertNotNull(r.getId());
    }

    @Test
    public void shouldReturnRegionName() {
        assertTrue(r.toString().equals("Western Europe"));
    }
}
