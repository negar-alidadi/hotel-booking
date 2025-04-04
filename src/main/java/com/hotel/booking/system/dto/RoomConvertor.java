package com.hotel.booking.system.dto;

import com.hotel.booking.system.entity.Hotel;
import com.hotel.booking.system.entity.Room;

import java.util.ArrayList;

public class RoomConvertor {
    public static RoomDTO convertToRoomDTO(Room room) {
//    HotelDTO hotelDTO = new HotelDTO(room.getHotel().getId(),
//            room.getHotel().getName(),
//            room.getHotel().getLocation(),
//            new ArrayList<>());

        RoomDTO roomDTO = new RoomDTO(
                room.getId(),
                room.getType(),
                room.getPrice(),
                room.getAvailable(),
                room.getHotel().getId()
        );
        return roomDTO;
    }
    public static Room convertDTOToRoom(RoomDTO roomDTO, Hotel hotel) {
//        Hotel hotel = new Hotel(roomDTO.getHotel().getId(),
//                roomDTO.getHotel().getName(),
//                roomDTO.getHotel().getLocation(),
//                new ArrayList<>());
        Room room = new Room(roomDTO.getId(),
                roomDTO.getType(),
                roomDTO.getPrice(),
                roomDTO.getAvailable(),
                hotel
        );
        return room;
    }
}
