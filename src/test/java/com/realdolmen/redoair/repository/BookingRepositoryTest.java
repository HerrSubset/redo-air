package com.realdolmen.redoair.repository;


import com.realdolmen.redoair.domain.Booking;
import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

public class BookingRepositoryTest extends JpaPersistenceTest {
    private BookingRepository repo;
    private static final Long TEST_BOOKING_ID = 5004L;

    @Before
    public void setUp() {
        repo = new BookingRepository();
        repo.em = entityManager();
    }

    @Test
    public void createShouldMakeNewBooking() {
        Booking b = new Booking();
        repo.create(b);
        assertNotNull(b.getId());
    }

    @Test
    public void updateShouldChangeBooking() {
        Booking b = entityManager().find(Booking.class, TEST_BOOKING_ID);
        Long number = b.getPayment().getCreditCard().getNumber();
        b.getPayment().getCreditCard().setNumber(number + 1);
        repo.update(b);
        assertTrue(b.getPayment().getCreditCard().getNumber() == number + 1);
        b = entityManager().find(Booking.class, TEST_BOOKING_ID);
        assertTrue(b.getPayment().getCreditCard().getNumber() == number + 1);
    }

    @Test
    public void deleteByObjectShouldRemoveBooking() {
        Booking b = entityManager().find(Booking.class, TEST_BOOKING_ID);
        repo.delete(b);
        assertNull(entityManager().find(Booking.class, TEST_BOOKING_ID));
    }

    @Test
    public void deleteByIdShouldRemoveBooking() {
        repo.delete(TEST_BOOKING_ID);
        assertNull(entityManager().find(Booking.class, TEST_BOOKING_ID));
    }
}
