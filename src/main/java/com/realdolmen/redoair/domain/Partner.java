package com.realdolmen.redoair.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Partner class.
 *
 * Stores information on a partner of ReDo air.
 */
@Entity
public class Partner {
    @Id
    @GeneratedValue
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
