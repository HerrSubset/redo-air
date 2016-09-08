package com.realdolmen.redoair.domain;

import com.realdolmen.redoair.AbstractEntity;

import javax.persistence.*;

/**
 * Airport class
 *
 * Represents an airport. An airport is situated in a region and has a code.
 */
@Entity
public class Airport extends AbstractEntity {
    @OneToOne
    @JoinColumn(name = "region_fk")
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

    @Override
    public String toString() {
        return code + " - " + region;
    }
}
