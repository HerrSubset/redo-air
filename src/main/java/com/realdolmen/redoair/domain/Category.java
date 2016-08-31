package com.realdolmen.redoair.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Category class.
 *
 * A category belongs to a flight. It's a collection of seats that have the
 * same pricing. Examples are economy seats or business class seats.
 *
 * The partner will offer us a certain amount of seats we can sell, this info
 * is stored in this class.
 */
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    private int maxNumberOfSeats;
    private double basePrice;
    private double baseMarkupPercentage;

    /***********************************************************
     * Constructors
     ***********************************************************/
    public Category() {

    }

    public Category(int maxNumberOfSeats, double basePrice, double baseMarkupPercentage) {
        this.maxNumberOfSeats = maxNumberOfSeats;
        this.basePrice = basePrice;
        this.baseMarkupPercentage = baseMarkupPercentage;
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

    public int getMaxNumberOfSeats() {
        return maxNumberOfSeats;
    }

    public void setMaxNumberOfSeats(int maxNumberOfSeats) {
        this.maxNumberOfSeats = maxNumberOfSeats;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getBaseMarkupPercentage() {
        return baseMarkupPercentage;
    }

    public void setBaseMarkupPercentage(double baseMarkupPercentage) {
        this.baseMarkupPercentage = baseMarkupPercentage;
    }
}
