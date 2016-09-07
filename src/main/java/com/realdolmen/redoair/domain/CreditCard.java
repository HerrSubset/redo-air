package com.realdolmen.redoair.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CreditCard implements Serializable {
    private Long number;

    public CreditCard() {

    }

    public CreditCard(long number) {
        this.number = number;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
