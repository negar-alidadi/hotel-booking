package com.hotel.booking.system.service;

import com.hotel.booking.system.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    BookingDTO bookRoom(BookingDTO bookingDTO);
    String cancelBooking(Long id);
    List<BookingDTO> findAllBookings();
}
