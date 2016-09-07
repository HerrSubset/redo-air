package com.realdolmen.redoair.domain;

/**
 * Utility class to contain a first and last name of a person.
 */
public class NameContainer {
    private String firstName;
    private String lastName;

    public NameContainer() {
    }

    public NameContainer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
