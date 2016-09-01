package com.realdolmen.redoair.repository;


import com.realdolmen.redoair.domain.Region;
import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

public class RegionRepositoryTest extends JpaPersistenceTest {
    private RegionRepository repo;
    private static final Long TEST_REGION_ID = 5004L;

    @Before
    public void setUp() {
        repo = new RegionRepository();
        repo.em = entityManager();
    }

    @Test
    public void createShouldMakeNewRegion() {
        Region r = new Region("Antarctica");
        repo.create(r);
        assertNotNull(r.getId());
    }

    @Test
    public void updateShouldChangeRegion() {
        Region r = entityManager().find(Region.class, TEST_REGION_ID);
        r.setName("Antarctica");
        repo.update(r);
        r = entityManager().find(Region.class, TEST_REGION_ID);
        assertEquals("Antarctica", r.getName());
    }

    @Test
    public void deleteByObjectShouldRemoveRegion() {
        Region r = entityManager().find(Region.class, TEST_REGION_ID);
        repo.delete(r);
        assertNull(entityManager().find(Region.class, TEST_REGION_ID));
    }

    @Test
    public void deleteByIdShouldRemoveRegion() {
        repo.delete(TEST_REGION_ID);
        assertNull(entityManager().find(Region.class, TEST_REGION_ID));
    }

    @Test
    public void findByIdReturnsCorrectRegion() {
        assertSame(entityManager().find(Region.class, TEST_REGION_ID), repo.findById(TEST_REGION_ID));
    }

    @Test
    public void invalidIdReturnsNull() {
        assertNull(repo.findById(9000L));
    }
}
