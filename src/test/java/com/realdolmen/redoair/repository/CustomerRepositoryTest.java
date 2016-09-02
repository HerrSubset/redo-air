package com.realdolmen.redoair.repository;

import com.realdolmen.redoair.domain.Customer;
import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

public class CustomerRepositoryTest extends JpaPersistenceTest {
    private CustomerRepository repo;
    private static final Long TEST_CUSTOMER_ID = 5003L;

    @Before
    public void setUp() {
        repo = new CustomerRepository();
        repo.em = entityManager();
    }

    @Test
    public void createShouldMakeNewCustomer() {
        Customer c = new Customer("Tom", "Boonen", "tommekebonen@example.com");
        repo.create(c);
        assertNotNull(c.getId());
    }

    @Test
    public void updateShouldChangeCustomer() {
        Customer c = entityManager().find(Customer.class, TEST_CUSTOMER_ID);
        c.setFirstName("Bert");
        repo.update(c);
        c = entityManager().find(Customer.class, TEST_CUSTOMER_ID);
        assertEquals("Bert", c.getFirstName());
    }

    @Test
    public void deleteByObjectShouldRemoveCustomer() {
        Customer c = entityManager().find(Customer.class, TEST_CUSTOMER_ID);
        repo.delete(c);
        assertNull(entityManager().find(Customer.class, TEST_CUSTOMER_ID));
    }

    @Test
    public void deleteByIdShouldRemoveCustomer() {
        repo.delete(TEST_CUSTOMER_ID);
        assertNull(entityManager().find(Customer.class, TEST_CUSTOMER_ID));
    }

    @Test
    public void findByIdReturnsCorrectCustomer() {
        assertSame(entityManager().find(Customer.class, TEST_CUSTOMER_ID), repo.findById(TEST_CUSTOMER_ID));
    }

    @Test
    public void invalidIdReturnsNull() {
        assertNull(repo.findById(9000L));
    }

    @Test
    public void findAllReturnsCorrectNumberOfCustomer() {
        assertEquals(4, repo.findAll().size());
    }
}
