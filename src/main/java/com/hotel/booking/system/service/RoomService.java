package com.hotel.booking.system.service;

import com.hotel.booking.system.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    RoomDTO findRoomById(Long id);
    List<RoomDTO> findAllRooms();
    RoomDTO saveRoom(RoomDTO room);
    RoomDTO updateRoom(Long id,RoomDTO room);
    String deleteRoom(Long id);
}
