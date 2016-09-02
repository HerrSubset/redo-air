package com.realdolmen.redoair.service;

import com.realdolmen.redoair.domain.Category;
import com.realdolmen.redoair.domain.FlightCombo;
import com.realdolmen.redoair.repository.CategoryRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
public class CategoryService {
    @Inject
    CategoryRepository repo;

    public List<String> getAllCategoryNames() {
        return repo.findAllNames();
    }

    public List<Category> getAllFlights() {
        return repo.findAll();
    }

    public List<Category> getFilteredFlights(String departureAirport, String arrivalAirport, Date date,
                                             String className, Integer numberOfPeople, String airline) {
        return repo.getFilteredFlights(departureAirport, arrivalAirport, date, className,
                                        numberOfPeople, airline);

    }

    /**
     * This method returns a list of FlightCombos containing departure flights and return flights,
     * if a return date was specified.
     */
    public List<FlightCombo> getFlightCombos(String departureAirport, String arrivalAirport, Date departureDate,
                                             Date returnDate, String className, Integer numberOfPeople,
                                             String airline) {
        ArrayList<FlightCombo> res = new ArrayList<>();

        List<Category> departureFlights = this.getFilteredFlights(departureAirport, arrivalAirport, departureDate,
                                                                className, numberOfPeople, airline);

        List<Category> returnFlights = null;
        if (returnDate != null){
            System.out.println("Searching flights for " + returnDate.toString());
            returnFlights = this.getFilteredFlights(arrivalAirport, departureAirport, returnDate, className,
                                                        numberOfPeople, airline);
            System.out.println("Found " + returnFlights.size() + " items");
        }

        // Create result list. If arrivalFlights are present, create FlightCombos with both types of flights.
        // Otherwise create FlightCombos with only departure flights.
        for (Category departureFlight: departureFlights) {
            if (returnFlights != null) {
                for (Category returnFlight: returnFlights) {
                    res.add(new FlightCombo(departureFlight, returnFlight));
                }
            } else {
                res.add(new FlightCombo(departureFlight, null));
            }
        }

        return res;
    }
}
