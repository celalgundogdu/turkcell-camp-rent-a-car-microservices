package com.turkcellcamp.paymentservice.business.rules;

import com.turkcellcamp.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.turkcellcamp.commonpackage.utils.exceptions.BusinessException;
import com.turkcellcamp.commonpackage.utils.exceptions.EntityAlreadyExistsException;
import com.turkcellcamp.commonpackage.utils.exceptions.EntityNotFoundException;
import com.turkcellcamp.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentBusinessRules {

    private final PaymentRepository repository;

    public void checkIfPaymentExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Payment not found");
        }
    }

    public void checkIfCardExists(String cardNumber) {
        if (repository.existsByCardNumber(cardNumber)) {
            throw new EntityAlreadyExistsException("Card already exists");
        }
    }

    public void checkIfPaymentIsValid(CreateRentalPaymentRequest request) {
        if (!repository.existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                request.getCardNumber(),
                request.getCardHolder(),
                request.getCardExpirationYear(),
                request.getCardExpirationMonth(),
                request.getCardCvv()
        )) {
            throw new BusinessException("Payment is invalid");
        }
    }

    public void checkIfBalanceIsEnough(double price, double balance) {
        if (price > balance) {
            throw new BusinessException("Balance is not enough");
        }
    }
}
