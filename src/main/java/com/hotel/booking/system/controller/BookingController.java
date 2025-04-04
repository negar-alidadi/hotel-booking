package com.hotel.booking.system.controller;

import com.hotel.booking.system.dto.BookingDTO;
import com.hotel.booking.system.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        return new ResponseEntity<>(bookingService.bookRoom(bookingDTO), HttpStatus.CREATED);
    }
    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Booking cancelled");
    }
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getBookings() {
        return ResponseEntity.ok(bookingService.findAllBookings());
    }
}
