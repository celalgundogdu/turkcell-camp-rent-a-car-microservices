package com.turkcellcamp.paymentservice.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPaymentsResponse {

    private UUID id;
    private String cardNumber;
    private String cardHolder;
    private double balance;
}
