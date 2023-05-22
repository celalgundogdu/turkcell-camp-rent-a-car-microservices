package com.turkcellcamp.paymentservice.business.abstracts;

import com.turkcellcamp.commonpackage.utils.dto.ClientResponse;
import com.turkcellcamp.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.turkcellcamp.paymentservice.business.dto.requests.CreatePaymentRequest;
import com.turkcellcamp.paymentservice.business.dto.requests.UpdatePaymentRequest;
import com.turkcellcamp.paymentservice.business.dto.responses.CreatePaymentResponse;
import com.turkcellcamp.paymentservice.business.dto.responses.GetAllPaymentsResponse;
import com.turkcellcamp.paymentservice.business.dto.responses.GetPaymentResponse;
import com.turkcellcamp.paymentservice.business.dto.responses.UpdatePaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    List<GetAllPaymentsResponse> getAll();

    GetPaymentResponse getById(UUID id);

    CreatePaymentResponse add(CreatePaymentRequest request);

    UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request);

    void delete(UUID id);

    ClientResponse processRentalPayment(CreateRentalPaymentRequest request);
}
