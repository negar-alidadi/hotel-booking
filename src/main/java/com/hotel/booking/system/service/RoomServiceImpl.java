package com.hotel.booking.system.service;

import com.hotel.booking.system.dto.RoomConvertor;
import com.hotel.booking.system.dto.RoomDTO;
import com.hotel.booking.system.entity.Hotel;
import com.hotel.booking.system.entity.Room;
import com.hotel.booking.system.exception.ResourceNotFoundEx;
import com.hotel.booking.system.exception.RoomNotFoundEx;
import com.hotel.booking.system.repository.HotelRepo;
import com.hotel.booking.system.repository.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepo roomRepo;
    private final HotelRepo hotelRepo;
    @Override
    public RoomDTO findRoomById(Long id) {
        Room room = roomRepo.findById(id)
                .orElseThrow(()-> new RoomNotFoundEx("no room found with id: " + id));
        return RoomConvertor.convertToRoomDTO(room);
    }

    @Override
    public List<RoomDTO> findAllRooms() {
        List<Room> rooms = roomRepo.findAll();
        List<RoomDTO> roomDTOs = rooms.stream()
                .map(RoomConvertor::convertToRoomDTO)
                .collect(Collectors.toList());
        return roomDTOs;
    }

    @Override
    public RoomDTO saveRoom(RoomDTO room) {
        Hotel hotel = hotelRepo.findById(room.getHotelId())
                .orElseThrow(()-> new ResourceNotFoundEx("no hotel found with id: " + room.getHotelId()));;
        Room room1 = RoomConvertor.convertDTOToRoom(room, hotel);
        Room savedRoom = roomRepo.save(room1);
        return RoomConvertor.convertToRoomDTO(savedRoom);
    }

    @Override
    public RoomDTO updateRoom(Long id, RoomDTO room) {
        Room existingRoom = roomRepo.findById(id)
                .orElseThrow(()-> new RoomNotFoundEx("no room found with id: " + id));;
        existingRoom.setType(room.getType());
        existingRoom.setAvailable(room.getAvailable());
        existingRoom.setPrice(room.getPrice());
        existingRoom.setHotel(existingRoom.getHotel());
        Room updatedRoom = roomRepo.save(existingRoom);
        return RoomConvertor.convertToRoomDTO(updatedRoom);
    }

    @Override
    public String deleteRoom(Long id) {
        Room room = roomRepo.findById(id)
                .orElseThrow(()-> new RoomNotFoundEx("no room found with id: " + id));;
        roomRepo.delete(room);
        return "room deleted";
    }
}
