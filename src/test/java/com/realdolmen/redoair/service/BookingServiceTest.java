package com.realdolmen.redoair.service;

import com.realdolmen.redoair.domain.*;
import com.realdolmen.redoair.repository.BookingRepository;
import com.realdolmen.redoair.repository.TicketRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {
    @InjectMocks
    private BookingService service;

    @Mock
    private BookingRepository bookingRepo;

    @Mock
    private TicketRepository ticketRepo;
    private List<NameContainer> passengerNames;
    private Category departureFlight;
    private Category returnFlight;
    private Payment payment;

    @Before
    public void setUp() {
        passengerNames = new ArrayList<>();
        passengerNames.add(new NameContainer("Alain", "Van Dam"));
        passengerNames.add(new NameContainer("Guido", "Pallemans"));
        passengerNames.add(new NameContainer("Michel", "Drets"));

        departureFlight = new Category(11, 150.0, 0.05);
        returnFlight = new Category(6, 130.0, 0.05);
        payment = new Payment(PaymentType.CREDITCARD, new CreditCard(15L));
    }



    @Test
    public void getBookingReturnsObjectGivenByRepo() {
        Booking b = new Booking();
        Mockito.when(bookingRepo.findById(10L)).thenReturn(b);
        Assert.assertSame(b, service.getBooking(10L));
    }

    @Test
    public void createBookingCallsRepoToCreateBooking() {
        service.createBooking(passengerNames, departureFlight, returnFlight, payment);
        Booking b = new Booking(payment);
        Mockito.verify(bookingRepo).create(any(Booking.class));
    }

    @Test
    public void createBookingCreatesOneTicketForEveryPassengerWhenReturnFlightIsNotNull() {
        service.createBooking(passengerNames, departureFlight, null, payment);
        Mockito.verify(ticketRepo, times(passengerNames.size())).create(any(Ticket.class));
    }

    @Test
    public void createBookingCreatesTwoTicketsForeEveryPassengerWhenReturnFlightIsNotNull() {
        service.createBooking(passengerNames, departureFlight, returnFlight, payment);
        Mockito.verify(ticketRepo, times(passengerNames.size() * 2)).create(any(Ticket.class));
    }
}
