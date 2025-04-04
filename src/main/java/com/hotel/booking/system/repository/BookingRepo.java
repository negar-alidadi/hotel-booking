package com.hotel.booking.system.repository;

import com.hotel.booking.system.entity.Booking;
import com.hotel.booking.system.entity.BookingStatus;
import com.hotel.booking.system.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByRoom(Room room);
    List<Booking> findByRoomAndStatus(Room room, BookingStatus status);
}
