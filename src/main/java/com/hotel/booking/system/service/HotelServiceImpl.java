package com.hotel.booking.system.service;

import com.hotel.booking.system.dto.HotelConvertor;
import com.hotel.booking.system.dto.HotelDTO;
import com.hotel.booking.system.dto.RoomConvertor;
import com.hotel.booking.system.entity.Hotel;
import com.hotel.booking.system.entity.Room;
import com.hotel.booking.system.exception.ResourceNotFoundEx;
import com.hotel.booking.system.repository.HotelRepo;
import com.hotel.booking.system.repository.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepo hotelRepo;

    @Override
    public HotelDTO findHotelById(Long id) {
        Hotel hotel = hotelRepo.findById(id)
                .orElseThrow(() ->new ResourceNotFoundEx("no hotel found with id: " + id));
        return HotelConvertor.conertToHotelDTO(hotel);
    }

    @Override
    public List<HotelDTO> findAllHotels() {
        List<Hotel> hotels = hotelRepo.findAll();
        List<HotelDTO> hotelDTOS = hotels.stream()
                .map(HotelConvertor::conertToHotelDTO)
                .collect(Collectors.toList());
        return hotelDTOS;
    }

    @Override
    public HotelDTO save(HotelDTO hotel) {
        Hotel hotel1 = HotelConvertor.hotelDTOToHotel(hotel);
        Hotel hotel2 = hotelRepo.save(hotel1);
        return HotelConvertor.conertToHotelDTO(hotel2);
    }

    @Override
    public HotelDTO updateHotel(Long id, HotelDTO hotel) {
        Hotel hotel1 = hotelRepo.findById(id)
                .orElseThrow(() ->new ResourceNotFoundEx("no hotel found with id: " + id));
        hotel1.setName(hotel.getName());
        hotel1.setLocation(hotel.getLocation());
        if (hotel.getRooms() != null && !hotel.getRooms().isEmpty()) {
        List<Room> room = hotel.getRooms().stream()
                .map(roomDTO -> RoomConvertor.convertDTOToRoom(roomDTO,hotel1))
                .collect(Collectors.toList());

        hotel1.getRooms().clear();
        hotel1.getRooms().addAll(room);
        }
        Hotel hotel2 = hotelRepo.save(hotel1);
        return HotelConvertor.conertToHotelDTO(hotel2);
    }

    @Override
    public String deleteHotel(Long id) {
        Hotel hotel = hotelRepo.findById(id)
                .orElseThrow(() ->new ResourceNotFoundEx("no hotel found with id: " + id));
        hotelRepo.delete(hotel);
        return "hotel deleted";
    }
}
