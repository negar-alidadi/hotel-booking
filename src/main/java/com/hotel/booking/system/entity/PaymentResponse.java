package com.hotel.booking.system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentResponse {
//    private String bookingReference;
//    private Double amount;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;
}
