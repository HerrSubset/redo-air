package com.realdolmen.redoair.domain__;


import com.realdolmen.redoair.domain.Category;
import com.realdolmen.redoair.domain.FlightCombo;
import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Test;

import java.util.Random;

public class FlightComboTest extends JpaPersistenceTest {

    @Test
    public void shouldCreateFlightCombo() {
        Category cret = new Category();
        Category cdep = new Category();
        FlightCombo flightCombo = new FlightCombo(cdep, cret);
        assertEquals(flightCombo.getReturnFlight(), cret);
        assertEquals(flightCombo.getDepartureFlight(), cdep);
    }

    @Test
    public void shouldGetTotalPrice() {
        Category cret = new Category();
        Category cdep = new Category();
        Random r = new Random();
        Double first = r.nextDouble();
        Double second = r.nextDouble();
        cret.setBasePrice(first);
        cdep.setBasePrice(second);
        FlightCombo flightCombo = new FlightCombo(cdep, cret);
        assertEquals(flightCombo.getTotalPrice(), (Double) (first + second));
    }


    @Test
    public void shouldSetAReturnFlight() {
        Category cret = new Category();
        Category cdep = new Category();
        Random r = new Random();
        Double first = r.nextDouble();
        Double second = r.nextDouble();
        cret.setBasePrice(first);
        cdep.setBasePrice(second);
        FlightCombo flightCombo = new FlightCombo(cdep);
        assertEquals(flightCombo.hasReturnFlight(), false);
        flightCombo.setReturnFlight(cret);
        assertEquals(flightCombo.hasReturnFlight(), true);
    }
}
