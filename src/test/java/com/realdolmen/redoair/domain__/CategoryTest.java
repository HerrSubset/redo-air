package com.realdolmen.redoair.domain__;
import com.realdolmen.redoair.domain.*;

import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class CategoryTest extends JpaPersistenceTest {
    private Category c;

    @Before
    public void setUp() {
        c = new Category(20, 90.22, 0.05);
    }

    @Test
    public void shouldSaveCategoryToDB() {
        assertNull(c.getId());
        entityManager().persist(c);
        assertNotNull(c.getId());
    }

    @Test
    public void testCalculatePrice() {
        assertEquals(94.731, c.getPrice(), 0);
    }

    @Test
    public void shouldDecreaseFreeSeatsBy5() {
        List<Ticket> tickets = new ArrayList<>();
        Ticket t = new Ticket("Nick", "Hanot",new Category());
        tickets.add(t);
        c.setTickets(tickets);
        assertEquals(c.getFreeSeats(), 19);
        tickets.add(t);
        tickets.add(t);
        tickets.add(t);
        tickets.add(t);
        tickets.add(t);
        c.setTickets(tickets);
        assertEquals(c.getFreeSeats(), 14);
    }

    @Test
    public void shouldGetFinalPrice() {
        List<Ticket> tickets = new ArrayList<>();
        Ticket t = new Ticket("Nick", "Hanot",new Category());
        tickets.add(t);
        c.setTickets(tickets);
        assertEquals(c.getFreeSeats(), 19);
        tickets.add(t);
        tickets.add(t);
        tickets.add(t);
        tickets.add(t);
        tickets.add(t);
        c.setTickets(tickets);
        assertEquals(c.getFinalPrice(), (Double) 94.731);
    }
}
