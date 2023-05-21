package com.turkcellcamp.rentalservice.business.rules;

import com.turkcellcamp.commonpackage.utils.dto.ClientResponse;
import com.turkcellcamp.commonpackage.utils.exceptions.BusinessException;
import com.turkcellcamp.commonpackage.utils.exceptions.EntityNotFoundException;
import com.turkcellcamp.rentalservice.api.clients.CarClient;
import com.turkcellcamp.rentalservice.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalBusinessRules {

    private final RentalRepository repository;
    private final CarClient carClient;

    public void checkIfRentalExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Model not found");
        }
    }

    public void ensureCarIsAvailable(UUID carId) {
        ClientResponse clientResponse = carClient.checkIfCarAvailable(carId);
        if (!clientResponse.isSuccess()) {
            throw new BusinessException(clientResponse.getMessage());
        }
    }
}
