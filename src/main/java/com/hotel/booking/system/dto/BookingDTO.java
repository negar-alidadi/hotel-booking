package com.hotel.booking.system.dto;

import com.hotel.booking.system.entity.BookingStatus;
import com.hotel.booking.system.entity.Room;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long id;
    private RoomDTO room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Double totalPrice;
    private BookingStatus status;
    private String bookingReference;
}
