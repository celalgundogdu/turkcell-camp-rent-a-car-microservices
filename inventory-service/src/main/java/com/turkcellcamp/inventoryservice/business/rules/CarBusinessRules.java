package com.turkcellcamp.inventoryservice.business.rules;

import com.turkcellcamp.commonpackage.utils.exceptions.BusinessException;
import com.turkcellcamp.commonpackage.utils.exceptions.EntityAlreadyExistsException;
import com.turkcellcamp.commonpackage.utils.exceptions.EntityNotFoundException;
import com.turkcellcamp.inventoryservice.entities.Car;
import com.turkcellcamp.inventoryservice.entities.enums.CarState;
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
            throw new EntityNotFoundException("Car not found");
        }
    }

    public void checkIfCarExistsByPlate(String plate) {
        if (repository.existsByPlateIgnoreCase(plate)) {
            throw new EntityAlreadyExistsException("Plate already exists");
        }
    }

    public void checkIfCarAvailable(UUID id) {
        Car car = repository.findById(id).orElseThrow();
        if (!car.getState().equals(CarState.AVAILABLE)) {
            throw new BusinessException("Car not available");
        }
    }
}
