package com.realdolmen.redoair.domain;

import com.realdolmen.redoair.AbstractEntity;

import javax.persistence.Entity;

/**
 * Region class.
 *
 * The information in this class is considered to be given by the partners of
 * ReDo air. It represents a region in the world (e.g. Western Europe).
 */
@Entity
public class Region extends AbstractEntity {
    private String name;



    /***********************************************************
     * Constructors
     ***********************************************************/

    public Region() {

    }

    public Region(String name) {
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
