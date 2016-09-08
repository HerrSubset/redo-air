package com.realdolmen.redoair.domain;

import com.realdolmen.redoair.AbstractEntity;

import javax.persistence.Entity;

/**
 * Partner class.
 *
 * Stores information on a partner of ReDo air.
 */
@Entity
public class Partner extends AbstractEntity {
    private String name;

    /***********************************************************
     * Constructors
     ***********************************************************/
    public Partner() {

    }

    public Partner(String name) {
        this.name = name;
    }



    /***********************************************************
     * Getters / Setters
     ***********************************************************/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
