package com.realdolmen.redoair.domain;


import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

public class TicketTest extends JpaPersistenceTest{
    private Ticket t;
    private static final Long TEST_CATEGORY_ID = 5000L;
    private Category c;

    @Before
    public void setUp() {
        c = entityManager().find(Category.class, TEST_CATEGORY_ID);
        t = new Ticket("George", "Orwell", c);
    }

    @Test
    public void ticketShouldBeSavedToDB() {
        assertNull(t.getId());
        entityManager().persist(t);
        assertNotNull(t.getId());
    }
}
