package com.realdolmen.redoair.service;

import com.realdolmen.redoair.domain.Category;
import com.realdolmen.redoair.domain.FlightCombo;
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
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
    @InjectMocks
    private CategoryService service;

    @Mock
    private CategoryRepository repo;
    private List<String> categoryNames;
    private List<Category> categories;
    private List<Category> categories2;

    @Before
    public void setUp(){
        categories = new ArrayList<>();
        categories.add(new Category(10, 150.0, 0.05));
        categories.add(new Category(5, 180.0, 0.05));

        categories2 = new ArrayList<>();
        categories2.add(new Category(5, 170.0, 0.05));
        categories2.add(new Category(10, 150.0, 0.05));
        categories2.add(new Category(5, 180.0, 0.05));

        categoryNames = new ArrayList<String>();
        categoryNames.add("business");
        categoryNames.add("economy");
    }

    @Test
    public void findAllNamesReturnsObjectGivenByRepo() {
        Mockito.when(repo.findAllNames()).thenReturn(categoryNames);
        Assert.assertSame(categoryNames, service.getAllCategoryNames());
    }

    @Test
    public void getAllFlightsReturnsObjectGivenByRepo() {
        Mockito.when(repo.findAll()).thenReturn(categories);
        Assert.assertSame(categories, service.getAllFlights());
    }

    @Test
    public void getFilteredFlightsReturnsObjectGivenByRepo() {
        Mockito.when(repo.getFilteredFlights(null, null, null, null, null, null)).thenReturn(categories);
        Assert.assertSame(categories, service.getFilteredFlights(null, null, null, null, null, null));
    }

    @Test
    public void getFlightByIdReturnsObjectGivenByRepo() {
        Category c = new Category();
        Mockito.when(repo.findById(1L)).thenReturn(c);
        Assert.assertSame(c, service.getFlightById(1L));
    }

    @Test
    public void getFlightCombosReturnsSameAmountOfObjectsAsTheRepoGivesItWhenReturnDateIsNull() {
        Date d = new Date();
        Date d2 = new Date(2016, 10, 10);
        Mockito.when(repo.getFilteredFlights("BRU", "VIE", d, "economy", 5, "Brussels Arilines")).thenReturn(categories);

        List<FlightCombo> flights = service.getFlightCombos("BRU", "VIE", d, null, "economy", 5, "Brussels Arilines");
        Assert.assertSame(flights.size(), categories.size());
    }

    @Test
    public void getFlightCombosReturnsProductOfReturnAndDepartureFlightsWhenReturnDateIsNotNull() {
        Date d = new Date();
        Date d2 = new Date(2016, 10, 10);
        Mockito.when(repo.getFilteredFlights("BRU", "VIE", d, "economy", 5, "Brussels Airlines")).thenReturn(categories);
        Mockito.when(repo.getFilteredFlights("VIE", "BRU", d2, "economy", 5, "Brussels Airlines")).thenReturn(categories2);

        List<FlightCombo> flights = service.getFlightCombos("BRU", "VIE", d, d2, "economy", 5, "Brussels Airlines");
        Assert.assertEquals(flights.size(), categories.size() * categories2.size());
    }
}
