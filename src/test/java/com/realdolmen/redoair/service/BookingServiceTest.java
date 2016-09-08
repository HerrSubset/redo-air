package com.realdolmen.redoair.service;

import com.realdolmen.redoair.domain.*;
import com.realdolmen.redoair.repository.BookingRepository;
import com.realdolmen.redoair.repository.CategoryRepository;
import com.realdolmen.redoair.repository.TicketRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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

    @Mock
    private CategoryRepository categoryRepo;
    private List<NameContainer> passengerNames;
    private Category departureFlight;
    private Category returnFlight;
    private Payment payment;

    private static final Long TEST_CATEGORYID_1 = 5001L;
    private static final Long TEST_CATEGORYID_2 = 5002L;

    @Before
    public void setUp() {
        passengerNames = new ArrayList<>();
        passengerNames.add(new NameContainer("Alain", "Van Dam"));
        passengerNames.add(new NameContainer("Guido", "Pallemans"));
        passengerNames.add(new NameContainer("Michel", "Drets"));

        departureFlight = new Category(11, 150.0, 0.05);
        returnFlight = new Category(6, 130.0, 0.05);
        departureFlight.setMaxNumberOfSeats(50);
        returnFlight.setMaxNumberOfSeats(50);
        departureFlight.setTickets(new ArrayList<Ticket>());
        returnFlight.setTickets(new ArrayList<Ticket>());
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
        departureFlight.setId(TEST_CATEGORYID_1);
        returnFlight.setId(TEST_CATEGORYID_2);
        Mockito.when(categoryRepo.findById(TEST_CATEGORYID_1)).thenReturn(departureFlight);
        Mockito.when(categoryRepo.findById(TEST_CATEGORYID_2)).thenReturn(returnFlight);
        service.createBooking(passengerNames, departureFlight, returnFlight, payment);
        Mockito.verify(bookingRepo).create(any(Booking.class));
    }

    @Test
    public void createBookingCreatesOneTicketForEveryPassengerWhenReturnFlightIsNotNull() {
        departureFlight.setId(TEST_CATEGORYID_1);
        returnFlight.setId(TEST_CATEGORYID_2);
        Mockito.when(categoryRepo.findById(TEST_CATEGORYID_1)).thenReturn(departureFlight);
        Mockito.when(categoryRepo.findById(TEST_CATEGORYID_2)).thenReturn(returnFlight);
        service.createBooking(passengerNames, departureFlight, null, payment);
        Mockito.verify(ticketRepo, times(passengerNames.size())).create(any(Ticket.class));
    }

    @Test
    public void createBookingCreatesTwoTicketsForEveryPassengerWhenReturnFlightIsNotNull() {
        departureFlight.setId(TEST_CATEGORYID_1);
        returnFlight.setId(TEST_CATEGORYID_2);
        Mockito.when(categoryRepo.findById(TEST_CATEGORYID_1)).thenReturn(departureFlight);
        Mockito.when(categoryRepo.findById(TEST_CATEGORYID_2)).thenReturn(returnFlight);
        service.createBooking(passengerNames, departureFlight, returnFlight, payment);
        Mockito.verify(ticketRepo, times(passengerNames.size() * 2)).create(any(Ticket.class));
    }
}
