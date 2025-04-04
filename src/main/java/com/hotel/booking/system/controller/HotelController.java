package com.hotel.booking.system.controller;

import com.hotel.booking.system.dto.HotelDTO;
import com.hotel.booking.system.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @PostMapping()
    public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotelDTO) {
        return new ResponseEntity<>(hotelService.save(hotelDTO), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable Long id,@RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.ok(hotelService.updateHotel(id,hotelDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.ok("Hotel deleted successfully");
    }
    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.findHotelById(id));
    }
    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        return ResponseEntity.ok(hotelService.findAllHotels());
    }

}
