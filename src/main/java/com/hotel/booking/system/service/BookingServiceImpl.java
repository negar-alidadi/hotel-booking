package com.hotel.booking.system.service;

import com.hotel.booking.system.dto.BookingConvertor;
import com.hotel.booking.system.dto.BookingDTO;
import com.hotel.booking.system.entity.Booking;
import com.hotel.booking.system.entity.BookingStatus;
import com.hotel.booking.system.entity.Room;
import com.hotel.booking.system.exception.InvalidBookingDateEx;
import com.hotel.booking.system.exception.NotAvailableRoomEx;
import com.hotel.booking.system.exception.ResourceNotFoundEx;
import com.hotel.booking.system.repository.BookingRepo;
import com.hotel.booking.system.repository.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepo bookingRepo;
    private final RoomRepo roomRepo;

    @Override
    @Transactional
    public BookingDTO bookRoom(BookingDTO bookingDTO) {
        Room room = roomRepo.findById(bookingDTO.getRoom().getId()).get();
        if (!room.getAvailable()){
            throw new NotAvailableRoomEx("Room is not available");
        }
        double totalPrace = calculatePrice(room,bookingDTO.getCheckIn(),bookingDTO.getCheckOut());
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setCheckIn(bookingDTO.getCheckIn());
        booking.setCheckOut(bookingDTO.getCheckOut());
        booking.setTotalPrice(totalPrace);
        booking.setStatus(BookingStatus.COMPLETED);
        room.setAvailable(false);
        roomRepo.save(room);
        Booking bookingSaved = bookingRepo.save(booking);
        return BookingConvertor.bookingToBookingDTO(bookingSaved);
    }

    @Override
    @Transactional
    public String cancelBooking(Long id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundEx("no booking found with id: " + id));
        booking.setStatus(BookingStatus.CANCELLED);
        Room room = booking.getRoom();
        room.setAvailable(true);
        roomRepo.save(room);
        bookingRepo.save(booking);
        return "booking cancelled";
    }

    @Override
    public List<BookingDTO> findAllBookings() {
        List<Booking> booking =bookingRepo.findAll();
        List<BookingDTO> bookingDTOs = booking.stream()
                .map(BookingConvertor::bookingToBookingDTO)
                .collect(Collectors.toList());
        return bookingDTOs;
    }

    private double calculatePrice(Room room, LocalDate checkIn, LocalDate checkOut) {
        long days = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (days == 0 && checkIn.isBefore(checkOut)) {
            throw new InvalidBookingDateEx("check in date is before check out date");
        }
        return room.getPrice() * days;
    }
}
