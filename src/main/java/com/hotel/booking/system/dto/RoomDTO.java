package com.hotel.booking.system.dto;

import com.hotel.booking.system.entity.Hotel;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Long id;
    private String type;
    @Positive(message = "price can not be negative")
    private double price;
    private Boolean available;
    @NotBlank(message = "hotel id should not be empty")
    private Long hotelId;
}
