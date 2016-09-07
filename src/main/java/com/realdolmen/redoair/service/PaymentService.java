package com.realdolmen.redoair.service;

import com.realdolmen.redoair.domain.CreditCard;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.io.Serializable;


/**
 * This class is a stub that would implement calling a payment provider and returning
 * a boolean that indicates whether the payment succeeded or not. Right now, it just
 * returns true for every payment.
 */
@Stateless
@LocalBean
public class PaymentService implements Serializable {

    public boolean payWithCreditCard(CreditCard c) {
        return true;
    }
}
