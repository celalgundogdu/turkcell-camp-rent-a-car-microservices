package com.turkcellcamp.commonpackage.events.invoice;

import com.turkcellcamp.commonpackage.events.Event;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceEvent implements Event {

    private String cardHolder;
    private String plate;
    private String modelName;
    private String brandName;
    private int modelYear;
    private double dailyPrice;
    private int rentedForDays;
    private LocalDate rentedAt;
}
