package com.realdolmen.redoair.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Payment {
    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @Embedded
    private CreditCard creditCard;

    public Payment() {

    }

    public Payment(PaymentType type) {
        this.type = type;
    }

    public Payment(PaymentType type, CreditCard c) {
        this.type = type;
        this.creditCard = c;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
