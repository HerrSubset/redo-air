package com.realdolmen.redoair.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * PriceOverride class
 *
 * This class is used by ReDo air to adjust the normal pricing scheme. It can
 * be used to set a fixed price, or give discounts.
 */
@Entity
public class PriceOverride {
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
