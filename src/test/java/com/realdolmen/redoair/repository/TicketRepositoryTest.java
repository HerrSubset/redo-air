package com.realdolmen.redoair.repository;


import com.realdolmen.redoair.domain.Category;
import com.realdolmen.redoair.domain.Ticket;
import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

public class TicketRepositoryTest extends JpaPersistenceTest {
    private TicketRepository repo;
    private static final Long TEST_CATEGORY_ID = 5000L;
    private static final Long TEST_TICKET_ID = 5004L;

    @Before
    public void setUp() {
        repo = new TicketRepository();
        repo.em = entityManager();
    }

    @Test
    public void createShouldMakeNewTicket() {
        Category c = entityManager().find(Category.class, TEST_CATEGORY_ID);
        Ticket t = new Ticket("John", "Steinbeck", c);
        repo.create(t);
        assertNotNull(t.getId());
    }

    @Test
    public void updateShouldChangeTicket() {
        Ticket t = entityManager().find(Ticket.class, TEST_TICKET_ID);
        t.setPassengerFirstName("Emperor");
        t = repo.update(t);
        assertEquals("Emperor", t.getPassengerFirstName());
        t = entityManager().find(Ticket.class, TEST_TICKET_ID);
        assertEquals("Emperor", t.getPassengerFirstName());
    }

    @Test
    public void deleteByObjectShouldRemoveObject() {
        Ticket t = entityManager().find(Ticket.class, TEST_TICKET_ID);
        repo.delete(t);
        assertNull(entityManager().find(Ticket.class, TEST_TICKET_ID));
    }

    @Test
    public void deleteByIdShouldRemoveObject() {
        repo.delete(TEST_TICKET_ID);
        assertNull(entityManager().find(Ticket.class, TEST_TICKET_ID));
    }

    @Test
    public void findByIdReturnsCorrectObject() {
        Ticket t = repo.findById(TEST_TICKET_ID);
        assertSame(t, entityManager().find(Ticket.class, TEST_TICKET_ID));
    }

    @Test
    public void invalidIdReturnsNull() {
        assertNull(repo.findById(9000L));
    }
}
