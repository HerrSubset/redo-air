package com.realdolmen.redoair.controller;


import com.realdolmen.redoair.domain.Booking;
import com.realdolmen.redoair.service.BookingService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

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
        if (booking == null && bookingId != null) {
            this.booking = service.getBooking(bookingId);
        }
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public boolean hasValidBooking() {
        return getBooking() != null;
    }

    public String generateQRcode() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getRequestURL().toString();
        String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
        return baseURL + "booking.jsf?bookingid=" + bookingId;
    }
}
