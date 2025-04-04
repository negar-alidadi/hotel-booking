package com.hotel.booking.system.exception;

public class ResourceNotFoundEx extends RuntimeException{
    public ResourceNotFoundEx(String message) {
        super(message);
    }
    public ResourceNotFoundEx() {}
}
