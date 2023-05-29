package com.turkcellcamp.invoiceservice.business.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {

    private String cardHolder;
    private String plate;
    private String modelName;
    private String brandName;
    private int modelYear;
    private double dailyPrice;
    private int rentedForDays;
    private LocalDateTime rentedAt;
}
