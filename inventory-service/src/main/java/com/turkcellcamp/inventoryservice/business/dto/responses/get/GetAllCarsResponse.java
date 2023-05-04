package com.turkcellcamp.inventoryservice.business.dto.responses.get;

import com.turkcellcamp.inventoryservice.entities.enums.CarState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCarsResponse {

    private UUID id;
    private String plate;
    private int modelYear;
    private double dailyPrice;
    private CarState state;
}
