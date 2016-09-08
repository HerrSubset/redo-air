package com.realdolmen.redoair.domain;

import com.realdolmen.redoair.AbstractEntity;

import javax.persistence.Entity;

/**
 * PriceOverride class
 *
 * This class is used by ReDo air to adjust the normal pricing scheme. It can
 * be used to set a fixed price, or give discounts.
 */
@Entity
public class PriceOverride extends AbstractEntity {
}
