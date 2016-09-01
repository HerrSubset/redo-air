package com.realdolmen.redoair.service;

import com.realdolmen.redoair.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
    @InjectMocks
    private CategoryService service;

    @Mock
    private CategoryRepository repo;
    private List<String> categoryNames;

    @Before
    public void setUp(){
        categoryNames = new ArrayList<String>();
        categoryNames.add("business");
        categoryNames.add("economy");
    }

    @Test
    public void findAllNamesReturnsObjectGivenByRepo() {
        Mockito.when(repo.findAllNames()).thenReturn(categoryNames);
        Assert.assertSame(categoryNames, service.getAllCategoryNames());
    }
}
