package com.hotel.booking.system.repository;

import com.hotel.booking.system.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Hotel, Long> {
}
