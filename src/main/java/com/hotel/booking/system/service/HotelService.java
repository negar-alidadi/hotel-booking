package com.hotel.booking.system.service;

import com.hotel.booking.system.dto.HotelDTO;

import java.util.List;

public interface HotelService {
     HotelDTO findHotelById(Long id);
     List<HotelDTO> findAllHotels();
     HotelDTO save(HotelDTO hotel);
     HotelDTO updateHotel(Long id, HotelDTO hotel);
     String deleteHotel(Long id);
}
