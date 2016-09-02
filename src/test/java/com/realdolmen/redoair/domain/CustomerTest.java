package com.realdolmen.redoair.domain;

import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

/**
 * Created by PSTBB36 on 31/08/2016.
 */
public class CustomerTest extends JpaPersistenceTest{
    private Customer c;

    @Before
    public void setUp() {
        c = new Customer("Benjamin", "Graham", "benjaminGraham@gmail.com");
    }

    @Test
    public void customerShouldBeSavedToDB() {
        assertNull(c.getId());
        entityManager().persist(c);
        assertNotNull(c.getId());
    }
}
