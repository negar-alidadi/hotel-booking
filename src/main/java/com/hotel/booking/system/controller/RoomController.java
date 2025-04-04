package com.hotel.booking.system.controller;

import com.hotel.booking.system.dto.RoomDTO;
import com.hotel.booking.system.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO) {
         return new ResponseEntity<>(roomService.saveRoom(roomDTO), HttpStatus.CREATED);
     }
    @PutMapping("/update/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable Long id,@RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok(roomService.updateRoom(id, roomDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("room deleted");
    }
    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.findRoomById(id));
    }
    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return ResponseEntity.ok(roomService.findAllRooms());
    }
}
