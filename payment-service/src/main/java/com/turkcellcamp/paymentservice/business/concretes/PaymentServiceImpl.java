package com.turkcellcamp.paymentservice.business.concretes;

import com.turkcellcamp.commonpackage.utils.dto.ClientResponse;
import com.turkcellcamp.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.turkcellcamp.commonpackage.utils.exceptions.BusinessException;
import com.turkcellcamp.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellcamp.paymentservice.business.abstracts.PaymentService;
import com.turkcellcamp.paymentservice.business.abstracts.PosService;
import com.turkcellcamp.paymentservice.business.dto.requests.CreatePaymentRequest;
import com.turkcellcamp.paymentservice.business.dto.requests.UpdatePaymentRequest;
import com.turkcellcamp.paymentservice.business.dto.responses.CreatePaymentResponse;
import com.turkcellcamp.paymentservice.business.dto.responses.GetAllPaymentsResponse;
import com.turkcellcamp.paymentservice.business.dto.responses.GetPaymentResponse;
import com.turkcellcamp.paymentservice.business.dto.responses.UpdatePaymentResponse;
import com.turkcellcamp.paymentservice.business.rules.PaymentBusinessRules;
import com.turkcellcamp.paymentservice.entities.Payment;
import com.turkcellcamp.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;
    private final ModelMapperService mapper;
    private final PaymentBusinessRules rules;
    private final PosService posService;

    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> paymentList = repository.findAll();
        List<GetAllPaymentsResponse> response = paymentList
                .stream()
                .map(payment -> mapper.forResponse().map(payment, GetAllPaymentsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetPaymentResponse getById(UUID id) {
        rules.checkIfPaymentExists(id);
        Payment payment = repository.findById(id).orElseThrow();
        GetPaymentResponse response = mapper.forResponse().map(payment, GetPaymentResponse.class);
        return response;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        rules.checkIfCardExists(request.getCardNumber());
        Payment payment = mapper.forRequest().map(request, Payment.class);
        payment.setId(null);
        Payment createdPayment = repository.save(payment);
        CreatePaymentResponse response = mapper.forResponse().map(createdPayment, CreatePaymentResponse.class);
        return response;
    }

    @Override
    public UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request) {
        rules.checkIfPaymentExists(id);
        Payment payment = mapper.forRequest().map(request, Payment.class);
        payment.setId(id);
        Payment updatedPayment = repository.save(payment);
        UpdatePaymentResponse response = mapper.forResponse().map(updatedPayment, UpdatePaymentResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfPaymentExists(id);
        repository.deleteById(id);
    }

    @Override
    public ClientResponse processRentalPayment(CreateRentalPaymentRequest request) {
        ClientResponse response = new ClientResponse();
        try {
            rules.checkIfPaymentIsValid(request);
            Payment payment = repository.findByCardNumber(request.getCardNumber());
            rules.checkIfBalanceIsEnough(request.getPrice(), payment.getBalance());
            posService.pay();
            payment.setBalance(payment.getBalance() - request.getPrice());
            repository.save(payment);

            response.setSuccess(true);
            response.setMessage("Payment is completed");
        } catch (BusinessException exception) {
            response.setSuccess(false);
            response.setMessage(exception.getMessage());
        }
        return response;
    }
}
