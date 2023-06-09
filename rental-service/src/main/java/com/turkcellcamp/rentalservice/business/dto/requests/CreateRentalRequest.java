package com.turkcellcamp.rentalservice.business.dto.requests;

import com.turkcellcamp.commonpackage.utils.dto.PaymentRequest;
import jakarta.validation.constraints.Min;
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
public class CreateRentalRequest {

    @NotNull
    private UUID carId;

    @Min(0)
    private double dailyPrice;

    @Min(1)
    private int rentedForDays;

    private PaymentRequest paymentRequest;
}
