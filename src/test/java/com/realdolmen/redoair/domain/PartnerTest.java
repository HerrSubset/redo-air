package com.realdolmen.redoair.domain;


import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

public class PartnerTest extends JpaPersistenceTest{
    private Partner p;

    @Before
    public void setUp() {
        p = new Partner("Brussels Airlines");
    }

    @Test
    public void shouldSavePartnerToDataBase() {
        assertNull(p.getId());
        entityManager().persist(p);
        assertNotNull(p.getId());
    }
}
