package com.turkcellcamp.rentalservice.api.clients;

import com.turkcellcamp.commonpackage.utils.dto.ClientResponse;
import com.turkcellcamp.commonpackage.utils.dto.CreateRentalPaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE", fallback = PaymentClientFallback.class)
public interface PaymentClient {

    @PutMapping("/api/v1/payments/process")
    ClientResponse processRentalPayment(@RequestBody CreateRentalPaymentRequest request);
}
