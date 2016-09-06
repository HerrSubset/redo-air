package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.Airport;
import com.realdolmen.redoair.domain.Category;
import com.realdolmen.redoair.service.AirportService;
import com.realdolmen.redoair.service.CategoryService;
import com.realdolmen.redoair.service.PartnerService;
import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Random;

/**
 * Created by NHOBB65 on 6/09/2016.
 */
public class IndexControllerTest extends JpaPersistenceTest {
    IndexController controller;

    @Before
    public void setUp() {
        controller = new IndexController();
        controller.setUp();
    }

    @Test
    public void shouldReturnCurrentDate() {
        Date d = new Date();
        assertEquals(controller.getCurrentDate(), d);

    }

    @Test
    public void shouldReturnReturnDate() {
        Date d = new Date();
        controller.setReturndate(d);
        assertEquals(controller.getReturndate(), d);
    }

    @Test
    public void shouldReturnDepartureDate() {
        Date d = new Date();
        controller.setDeparturedate(d);
        assertEquals(controller.getDeparturedate(), d);
    }

    @Test
    public void shouldSetAndReturnNumberOfPersons() {
        Random r = new Random();
        int i = r.nextInt();
        controller.setNumberOfPersons(i);
        assertEquals(controller.getNumberOfPersons(), i);
    }

    @Test
    public void shouldSetOneWayToFalse() {
        controller.setOneWay(false);
        assertFalse(controller.getOneWay());
    }

    @Test
    public void shouldSetOneWayToTrue() {
        controller.setOneWay(true);
        assertTrue(controller.getOneWay());
    }

    @Test
    public void shouldSetAndGetAirportService() {
        AirportService airportService = new AirportService();
        controller.setAirportService(airportService);
        assertSame(controller.getAirportService(), airportService);
    }

    @Test
    public void shouldSetAndGetPartnerService() {
        PartnerService partnerService = new PartnerService();
        controller.setPartnerService(partnerService);
        assertSame(controller.getPartnerService(), partnerService);
    }

    @Test
    public void shouldSetAndGetCategory() {
        Category category =  new Category();
        controller.setCategory("FIRSTCLASSS");
        assertSame(controller.getCategory(), "FIRSTCLASSS");
    }

    @Test
    public void shouldSetAndGetDestinationAirport() {
        controller.setDestinationAirport("NORTH EUROPE");
        assertSame(controller.getDestinationAirport(), "NORTH EUROPE");
    }

    @Test
    public void shouldSetAndGetDepartureAirport() {
        controller.setDepartureAirport("NORTH EUROPE");
        assertSame(controller.getDepartureAirport(), "NORTH EUROPE");
    }

}



