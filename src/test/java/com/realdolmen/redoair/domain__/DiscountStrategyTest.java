package com.realdolmen.redoair.domain__;
import com.realdolmen.redoair.domain.*;

import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by PSTBB36 on 31/08/2016.
 */
public class DiscountStrategyTest extends JpaPersistenceTest{
    private DiscountStrategy d;

    @Before
    public void setUp() {
        d = new DiscountStrategy();
    }

    @Test
    public void shouldSaveDiscountStrategyToDB() {
        assertNull(d.getId());
        entityManager().persist(d);
        assertNotNull(d.getId());
    }
}
