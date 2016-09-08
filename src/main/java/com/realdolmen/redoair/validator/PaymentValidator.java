package com.realdolmen.redoair.validator;

import com.realdolmen.redoair.domain.Payment;
import com.realdolmen.redoair.domain.PaymentType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * A payment is valid in the following cases:
 */
public class PaymentValidator implements ConstraintValidator<ValidPayment, Payment> {
    @Override
    public void initialize(ValidPayment validPayment) {

    }

    @Override
    public boolean isValid(Payment payment, ConstraintValidatorContext constraintValidatorContext) {
        boolean res = true;

        if (payment.getCreditCard() == null && payment.getType() == PaymentType.CREDITCARD)
            res = false;

        return res;
    }
}
