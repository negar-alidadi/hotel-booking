package com.hotel.booking.system.service;

import com.hotel.booking.system.dto.BookingConvertor;
import com.hotel.booking.system.dto.BookingDTO;
import com.hotel.booking.system.entity.*;
import com.hotel.booking.system.exception.InvalidBookingDateEx;
import com.hotel.booking.system.exception.NotAvailableRoomEx;
import com.hotel.booking.system.exception.ResourceNotFoundEx;
import com.hotel.booking.system.repository.BookingRepo;
import com.hotel.booking.system.repository.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepo bookingRepo;
    private final RoomRepo roomRepo;
    private final WebClient.Builder webClientBuilder;

    @Override
    @Transactional
    public BookingDTO bookRoom(BookingDTO bookingDTO) {
        Room room = roomRepo.findById(bookingDTO.getRoom().getId()).get();
        if (!room.getAvailable()){
            throw new NotAvailableRoomEx("Room is not available");
        }
        String reference = UUID.randomUUID().toString();
        double totalPrace = calculatePrice(room,bookingDTO.getCheckIn(),bookingDTO.getCheckOut());
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setCheckIn(bookingDTO.getCheckIn());
        booking.setCheckOut(bookingDTO.getCheckOut());
        booking.setTotalPrice(totalPrace);
        booking.setStatus(BookingStatus.PENDING);
        booking.setBookingReference(reference);

//        Booking bookingSaved = bookingRepo.save(booking);
//        return BookingConvertor.bookingToBookingDTO(bookingSaved);
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setBookingReference(booking.getBookingReference());
        paymentRequest.setRoomPrice(totalPrace);
        PaymentResponse response = webClientBuilder.build()
                .post()
                .uri("http://localhost:8081/payments/pay")
                .bodyValue(paymentRequest)
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .block();
        if (response == null || !"SUCCESS".equalsIgnoreCase(response.getPaymentStatus().toString())) {
            throw new IllegalArgumentException("failed to book room");
        }


        booking.setStatus(BookingStatus.COMPLETED);
        room.setAvailable(false);
        roomRepo.save(room);
        Booking saved = bookingRepo.save(booking);
        return BookingConvertor.bookingToBookingDTO(saved);
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
        webClientBuilder.build()
                .post()
                .uri("http://localhost:8081/payments/cancel/" + booking.getBookingReference())
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .block();
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
