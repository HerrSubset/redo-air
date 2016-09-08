package com.realdolmen.redoair.domain__;
import com.realdolmen.redoair.domain.*;

import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityTransaction;

/**
 * Created by PSTBB36 on 31/08/2016.
 */
public class BookingTest extends JpaPersistenceTest{
    private Booking b;

    @Before
    public void setUp() {
        Payment p = new Payment(PaymentType.CREDITCARD, new CreditCard(1));
        b = new Booking();
    }


    @Test
    public void shouldSaveBookingToDB() {
        assertNull(b.getId());
        entityManager().persist(b);
        assertNotNull(b.getId());
    }

    @Test(expected = java.lang.IllegalStateException.class)
    public void createBookingWithInvalidPaymentShouldThrowError() {
        EntityTransaction transaction = entityManager().getTransaction();
        transaction.begin();
        Payment p = new Payment(PaymentType.CREDITCARD);
        assertNull(p.getCreditCard());
        b.setPayment(p);
        entityManager().persist(b);
        transaction.commit();
    }
}
