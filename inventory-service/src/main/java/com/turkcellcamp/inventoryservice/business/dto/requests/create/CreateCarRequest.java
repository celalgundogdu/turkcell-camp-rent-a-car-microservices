package com.turkcellcamp.inventoryservice.business.dto.requests.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {

    @NotBlank
    private String plate;

    @Min(value = 2010)
    private int modelYear;

    @Min(value = 0)
    private double dailyPrice;

    @NotNull
    private UUID modelId;
}
