package com.realdolmen.redoair.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Booking class
 *
 * Represents a collection of tickets that were bought simultaneously.
 * It also holds information on how the order was paid for.
 */
@Entity
public class Booking {
    @Id
    @GeneratedValue
    private long id;

    /***********************************************************
     * Constructors
     ***********************************************************/
    public Booking() {

    }


    /***********************************************************
     * Getters / Setters
     ***********************************************************/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
