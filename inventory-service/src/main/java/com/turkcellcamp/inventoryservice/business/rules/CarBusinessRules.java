package com.turkcellcamp.inventoryservice.business.rules;

import com.turkcellcamp.inventoryservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CarBusinessRules {

    private final CarRepository repository;

    public void checkIfCarExistsById(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Car not found");
        }
    }

    public void checkIfCarExistsByPlate(String plate) {
        if (repository.existsByPlateIgnoreCase(plate)) {
            throw new RuntimeException("Plate already exists");
        }
    }
}
