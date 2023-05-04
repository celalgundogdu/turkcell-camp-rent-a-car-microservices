package com.turkcellcamp.inventoryservice.entities;

import com.turkcellcamp.inventoryservice.entities.enums.CarState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String plate;

    private int modelYear;

    private double dailyPrice;

    private CarState state;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
}
