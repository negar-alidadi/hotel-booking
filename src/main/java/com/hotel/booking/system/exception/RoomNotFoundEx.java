package com.hotel.booking.system.exception;

public class RoomNotFoundEx extends RuntimeException{
    public RoomNotFoundEx(String message) {
        super(message);
    }
    public RoomNotFoundEx() {}
}
