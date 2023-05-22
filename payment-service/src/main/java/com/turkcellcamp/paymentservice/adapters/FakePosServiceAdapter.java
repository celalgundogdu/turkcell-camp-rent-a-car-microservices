package com.turkcellcamp.paymentservice.adapters;

import com.turkcellcamp.paymentservice.business.abstracts.PosService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakePosServiceAdapter implements PosService {

    @Override
    public void pay() {
        boolean isPaymentSuccessful = new Random().nextBoolean();
        if (!isPaymentSuccessful) {
            throw new RuntimeException("Payment is unsuccessful");
        }
    }
}
