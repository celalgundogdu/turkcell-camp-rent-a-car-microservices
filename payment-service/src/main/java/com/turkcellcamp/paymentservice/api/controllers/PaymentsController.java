package com.turkcellcamp.paymentservice.api.controllers;

import com.turkcellcamp.commonpackage.utils.dto.ClientResponse;
import com.turkcellcamp.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.turkcellcamp.paymentservice.business.abstracts.PaymentService;
import com.turkcellcamp.paymentservice.business.dto.requests.CreatePaymentRequest;
import com.turkcellcamp.paymentservice.business.dto.requests.UpdatePaymentRequest;
import com.turkcellcamp.paymentservice.business.dto.responses.CreatePaymentResponse;
import com.turkcellcamp.paymentservice.business.dto.responses.GetAllPaymentsResponse;
import com.turkcellcamp.paymentservice.business.dto.responses.GetPaymentResponse;
import com.turkcellcamp.paymentservice.business.dto.responses.UpdatePaymentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentsController {

    private final PaymentService service;

    @GetMapping
    public List<GetAllPaymentsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@Valid @PathVariable UUID id, @RequestBody UpdatePaymentRequest request) {
        return service.update(id, request);
    }

    @PutMapping("/process")
    public ClientResponse processRentalPayment(@RequestBody CreateRentalPaymentRequest request) {
        return service.processRentalPayment(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
