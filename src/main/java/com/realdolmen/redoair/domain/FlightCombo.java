package com.realdolmen.redoair.domain;

/**
 * FlightCombo class.
 *
 * This class is a container for two Categories. They are used in the front-end
 * to combine a departure and return flight and allow easy access to their data.
 */
public class FlightCombo {
    private Category departureFlight;
    private Category returnFlight;

    /***********************************************************
     * Constructors
     ***********************************************************/

    public FlightCombo(Category departure) {
        this.departureFlight = departure;
    }

    public FlightCombo(Category departureFlight, Category returnFlight) {
        this.departureFlight = departureFlight;
        this.returnFlight = returnFlight;
    }



    /***********************************************************
     * Getters / Setters
     ***********************************************************/

    public Category getDepartureFlight() {
        return departureFlight;
    }

    public void setDepartureFlight(Category departureFlight) {
        this.departureFlight = departureFlight;
    }

    public Category getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(Category returnFlight) {
        this.returnFlight = returnFlight;
    }



    /***********************************************************
     * Other Methods
     ***********************************************************/
    public Double getTotalPrice() {
        Double res = departureFlight.getPrice();

        if (returnFlight != null)
            res += returnFlight.getPrice();

        return res;
    }

    public boolean hasReturnFlight() {
        return returnFlight != null;
    }
}
