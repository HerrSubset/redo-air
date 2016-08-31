package com.realdolmen.redoair.domain;

import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;


public class CategoryTest extends JpaPersistenceTest {
    private Category c;

    @Before
    public void setUp() {
        c = new Category(20, 14.23, 0.05);
    }

    @Test
    public void shouldSaveCategoryToDB() {
        assertNull(c.getId());
        entityManager().persist(c);
        assertNotNull(c.getId());
    }
}
