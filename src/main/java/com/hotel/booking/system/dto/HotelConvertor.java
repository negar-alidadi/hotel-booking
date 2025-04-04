package com.hotel.booking.system.dto;

import com.hotel.booking.system.entity.Hotel;
import com.hotel.booking.system.entity.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HotelConvertor {
    public static HotelDTO conertToHotelDTO(Hotel hotel) {
        List<RoomDTO> roomDTOs = hotel.getRooms().stream()
                .map(RoomConvertor::convertToRoomDTO)
                .collect(Collectors.toList());
        HotelDTO hotelDTO = new HotelDTO(
                hotel.getId(),
                hotel.getName(),
                hotel.getLocation(),
                roomDTOs
        );
        return hotelDTO;
    }
    public static Hotel hotelDTOToHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
            hotel.setId(hotelDTO.getId());
            hotel.setName(hotelDTO.getName());
            hotel.setLocation(hotelDTO.getLocation());
//        List<Room> rooms = hotelDTO.getRooms().stream()
//                .map(roomDTO -> RoomConvertor.convertDTOToRoom(roomDTO,hotel))
//                .collect(Collectors.toList());
        hotel.setRooms(new ArrayList<>());
        return hotel;
    }
}
