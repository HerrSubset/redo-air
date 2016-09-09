package com.realdolmen.redoair.controller;

import com.realdolmen.course.QrCode;
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

    public boolean bookingWentWrong() {
        return bookingId == null;
    }

    public String generateQRcode() {
//        QrCode qrCode = new QrCode();
//        qrCode.generateQrCode(Long.toString(bookingId));
//        System.out.println("qr code generated");
//        return Long.toString(bookingId) + ".JPG";
        return "http://localhost:8080/redo-air/booking.jsf?bookingid=" + bookingId;
    }




    private static final long serialVersionUID = 20120316L;
    private String renderMethod;
    private String text;
    private String label;
    private int mode;
    private int size;
    private String fillColor;

}
