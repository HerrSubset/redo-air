package com.realdolmen.redoair.domain;

import com.realdolmen.redoair.AbstractEntity;

import javax.persistence.Entity;

/**
 * DiscountStrategy class
 *
 * When ReDo air sells enough seats of a certain category, the partner rewards
 * them with a reduction on the purchase price. The information on how much
 * seats have to be sold and how much reduction this gives is stored here
 */
@Entity
public class DiscountStrategy extends AbstractEntity {
}
