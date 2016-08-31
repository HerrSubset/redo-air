package com.realdolmen.redoair.repository;


import com.realdolmen.redoair.domain.Partner;
import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PartnerRepositoryTest extends JpaPersistenceTest {
    private PartnerRepository repo;
    private static final Long TEST_PARTNER_ID = 5004L;

    @Before
    public void setUp() {
        repo = new PartnerRepository();
        repo.em = entityManager();
    }

    @Test
    public void testShouldCreateNewPartner() {
        Partner p = new Partner("Austrian Airlines");
        repo.create(p);
        assertNotNull(p.getId());
    }

    @Test
    public void updateShouldChangePartner() {
        Partner p = entityManager().find(Partner.class, TEST_PARTNER_ID);
        p.setName("easyjet");
        p = repo.update(p);
        assertEquals("easyjet", p.getName());
        p = entityManager().find(Partner.class, TEST_PARTNER_ID);
        assertEquals("easyjet", p.getName());
    }

    @Test
    public void deleteByIdShouldRemovePartner() {
        repo.delete(TEST_PARTNER_ID);
        assertNull(entityManager().find(Partner.class, TEST_PARTNER_ID));
    }

    @Test
    public void deleteByObjectShouldRemovePartner() {
        Partner p = entityManager().find(Partner.class, TEST_PARTNER_ID);
        repo.delete(p);
        assertNull(entityManager().find(Partner.class, TEST_PARTNER_ID));
    }

    @Test
    public void findByIdReturnsCorrectPartner() {
        Partner p = entityManager().find(Partner.class, TEST_PARTNER_ID);
        assertSame(p, repo.findById(TEST_PARTNER_ID));
    }

    @Test
    public void invalidIdReturnsNull() {
        assertNull(repo.findById(9000L));
    }

    @Test
    public void findAllShouldReturnAllPartners() {
        List<Partner> all = repo.findAll();
        assertEquals(all.size(), 5);
    }
}
