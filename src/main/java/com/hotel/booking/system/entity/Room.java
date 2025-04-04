package com.hotel.booking.system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private double price;
    private Boolean available= true;
    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    @JsonBackReference
    private Hotel hotel;

}
