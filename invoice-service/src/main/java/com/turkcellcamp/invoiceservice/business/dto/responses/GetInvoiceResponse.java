package com.turkcellcamp.invoiceservice.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetInvoiceResponse {

    private String id;
    private String cardHolder;
    private String plate;
    private String modelName;
    private String brandName;
    private int modelYear;
    private double dailyPrice;
    private int rentedForDays;
    private LocalDate rentedAt;
}
