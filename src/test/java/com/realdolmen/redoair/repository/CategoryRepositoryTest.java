package com.realdolmen.redoair.repository;

import com.realdolmen.redoair.domain.Category;
import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class CategoryRepositoryTest extends JpaPersistenceTest {
    private CategoryRepository repo;
    private static final Long TEST_CATEGORY_ID = 5004L;


    @Before
    public void setUp() {
        repo = new CategoryRepository();
        repo.em = entityManager();
    }

    @Test
    public void createShouldMakeCategory() {
        Category c = new Category(20, 140.29, 0.05);
        repo.create(c);
        assertNotNull(c.getId());
    }

    @Test
    public void updateShouldChangeCategory() {
        Category c = entityManager().find(Category.class, TEST_CATEGORY_ID);
        c.setMaxNumberOfSeats(22);
        c = repo.update(c);
        assertEquals(22, c.getMaxNumberOfSeats());
        c = entityManager().find(Category.class, TEST_CATEGORY_ID);
        assertEquals(22, c.getMaxNumberOfSeats());
    }

    @Test
    public void deleteByIdShouldRemoveCategory() {
        repo.delete(TEST_CATEGORY_ID);
        assertNull(entityManager().find(Category.class, TEST_CATEGORY_ID));
    }

    @Test
    public void deleteByObjectShouldRemoveCategory() {
        Category c = entityManager().find(Category.class, TEST_CATEGORY_ID);
        repo.delete(c);
        assertNull(entityManager().find(Category.class, TEST_CATEGORY_ID));
    }

    @Test
    public void findByIdReturnsCorrectCategory() {
        Category c = entityManager().find(Category.class, TEST_CATEGORY_ID);
        assertSame(c, repo.findById(TEST_CATEGORY_ID));
    }

    @Test
    public void invalidIdReturnsNull() {
        assertNull(repo.findById(9000L));
    }

    @Test
    public void findAllNamesReturnsAllNames() {
        List<String> names = repo.findAllNames();
        assertEquals(2, names.size());
        assertTrue(names.contains("business"));
        assertTrue(names.contains("economy"));
    }
}
