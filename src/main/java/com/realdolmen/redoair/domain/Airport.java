package com.realdolmen.redoair.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Airport class
 *
 * Represents an airport. An airport is situated in a region and has a code.
 */
@Entity
public class Airport {
    @Id
    @GeneratedValue
    private long id;

    private Region region;
    private String code;



    /***********************************************************
     * Constructors
     ***********************************************************/
    public Airport() {

    }

    public Airport(Region region, String code) {
        this.region = region;
        this.code = code;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
