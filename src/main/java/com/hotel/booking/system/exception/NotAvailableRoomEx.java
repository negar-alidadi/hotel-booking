package com.hotel.booking.system.exception;

public class NotAvailableRoomEx extends RuntimeException{
    public NotAvailableRoomEx(String message) {
        super(message);
    }
    public NotAvailableRoomEx() {}
}
