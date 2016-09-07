package com.realdolmen.redoair.domain__;
import com.realdolmen.redoair.domain.*;


import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

public class TicketTest extends JpaPersistenceTest{
    private Ticket t;
    private static final Long TEST_CATEGORY_ID = 5000L;
    private static final Long TEST_BOOKING_ID = 5000L;
    private Category c;
    private Booking b;

    @Before
    public void setUp() {
        c = entityManager().find(Category.class, TEST_CATEGORY_ID);
        b = entityManager().find(Booking.class, TEST_BOOKING_ID);
        t = new Ticket("George", "Orwell", c, b);
    }

    @Test
    public void ticketShouldBeSavedToDB() {
        assertNull(t.getId());
        entityManager().persist(t);
        assertNotNull(t.getId());
    }
}
