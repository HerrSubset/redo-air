package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.Booking;
import com.realdolmen.redoair.service.BookingService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class BookingController {
    @Inject
    private BookingService service;

    private Long bookingId;
    private Booking booking;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Booking getBooking() {
        if (booking == null) {
            this.booking = service.getBooking(bookingId);
        }
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
