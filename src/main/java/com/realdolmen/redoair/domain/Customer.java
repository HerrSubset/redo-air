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
    private Long id;

    private String email;
    private String firstName;
    private String lastName;
    private String digest;      //used to store password hash



    /***********************************************************
     * Constructors
     ***********************************************************/

    public Customer() {

    }

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email=email;
    }



    /***********************************************************
     * Getters / Setters
     ***********************************************************/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
