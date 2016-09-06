package com.realdolmen.redoair.domain;


import com.mysql.jdbc.AssertionFailedException;
import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

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

    @Test
    public void shouldReturnPartnerName() {
        assertTrue(p.toString().equals("Brussels Airlines"));
    }
}
