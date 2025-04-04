package com.hotel.booking.system.repository;

import com.hotel.booking.system.entity.Hotel;
import com.hotel.booking.system.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepo extends JpaRepository<Room, Long> {
    List<Room> findByHotel(Hotel hotel);
    Optional<Room> findByIdAndAvailable(Long id, Boolean available);
}
