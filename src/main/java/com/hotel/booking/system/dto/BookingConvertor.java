package com.hotel.booking.system.dto;

import com.hotel.booking.system.entity.Booking;
import com.hotel.booking.system.entity.Room;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingConvertor {
    public static BookingDTO bookingToBookingDTO(Booking booking) {
        RoomDTO roomDTOS = new RoomDTO(booking.getRoom().getId(),
                booking.getRoom().getType(),
                booking.getRoom().getPrice(),
                booking.getRoom().getAvailable(),
                booking.getRoom().getHotel().getId());
        BookingDTO bookingDTO = new BookingDTO(booking.getId(),
                roomDTOS,
                booking.getCheckIn(),
                booking.getCheckOut(),
                booking.getTotalPrice(),
                booking.getStatus(),
                booking.getBookingReference()
                );
        return bookingDTO;
    }
    public static Booking bookingDTOToBooking(BookingDTO bookingDTO) {
        Room room = new Room();
        room.setId(bookingDTO.getRoom().getId());
        Booking booking = new Booking(
                bookingDTO.getId(),
                room,
                bookingDTO.getCheckIn(),
                bookingDTO.getCheckOut(),
                bookingDTO.getTotalPrice(),
                bookingDTO.getStatus(),
                bookingDTO.getBookingReference()
        );
        return booking;
    }
}
