package com.hotel.booking.system.entity;

import com.hotel.booking.system.dto.RoomDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentRequest {
    private String bookingReference;
    private RoomDTO roomDTO ;
    private Date checkInDate;
    private Date checkOutDate;
    private Double roomPrice;
}
