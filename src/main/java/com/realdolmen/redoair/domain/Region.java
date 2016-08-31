package com.realdolmen.redoair.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Region class.
 *
 * The information in this class is considered to be given by the partners of
 * ReDo air. It represents a region in the world (e.g. Western Europe).
 */
@Entity
public class Region {
    @Id
    @GeneratedValue
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
