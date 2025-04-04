package com.hotel.booking.system.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hotel.booking.system.entity.Room;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
    private Long id;
    @NotBlank(message = "name should ot be empty")
    private String name;
    private String location;
    private List<RoomDTO> rooms;
}
