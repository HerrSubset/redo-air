package com.realdolmen.redoair.domain;


import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

public class PriceOverrideTest extends JpaPersistenceTest {
    private PriceOverride p;

    @Before
    public void setUp() {
        p = new PriceOverride();
    }

    @Test
    public void shouldSavePriceOverrideToDB() {
        assertNull(p.getId());
        entityManager().persist(p);
        assertNotNull(p.getId());
    }
}
