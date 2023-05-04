package com.turkcellcamp.inventoryservice.business.dto.requests.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {

    @NotBlank
    private String plate;

    @Min(value = 2010)
    private int modelYear;

    @Min(value = 0)
    private double dailyPrice;

    @NotBlank
    private UUID modelId;
}
