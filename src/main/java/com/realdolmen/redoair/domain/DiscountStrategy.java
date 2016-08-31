package com.realdolmen.redoair.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * DiscountStrategy class
 *
 * When ReDo air sells enough seats of a certain category, the partner rewards
 * them with a reduction on the purchase price. The information on how much
 * seats have to be sold and how much reduction this gives is stored here
 */
@Entity
public class DiscountStrategy {
    @Id
    @GeneratedValue
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
