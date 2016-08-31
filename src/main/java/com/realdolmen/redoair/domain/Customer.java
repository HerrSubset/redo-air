package com.realdolmen.redoair.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Customer class.
 *
 * Represents a user of the website that can buy tickets and log in.
 */

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private long id;

    private String firstName;
    private String lastName;
    private String digest;      //used to store password hash



    /***********************************************************
     * Constructors
     ***********************************************************/

    public Customer() {

    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}
