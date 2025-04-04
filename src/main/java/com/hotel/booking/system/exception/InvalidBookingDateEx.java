package com.hotel.booking.system.exception;

public class InvalidBookingDateEx extends RuntimeException{
    public InvalidBookingDateEx(String message) {
        super(message);
    }
    public InvalidBookingDateEx() {}
}
