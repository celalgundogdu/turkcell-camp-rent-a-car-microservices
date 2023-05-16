package com.turkcellcamp.rentalservice.business.rules;

import com.turkcellcamp.commonpackage.utils.exceptions.EntityNotFoundException;
import com.turkcellcamp.rentalservice.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalBusinessRules {

    private final RentalRepository repository;

    public void checkIfRentalExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Model not found");
        }
    }
}
