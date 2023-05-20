package com.turkcellcamp.maintenanceservice.business.rules;

import com.turkcellcamp.commonpackage.utils.exceptions.EntityAlreadyExistsException;
import com.turkcellcamp.commonpackage.utils.exceptions.EntityNotFoundException;
import com.turkcellcamp.maintenanceservice.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MaintenanceRules {

    private final MaintenanceRepository repository;

    public void checkIfMaintenanceExistsById(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Maintenance not found");
        }
    }

    public void checkIfCarUnderMaintenance(UUID carId) {
        if (repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new EntityAlreadyExistsException("Car is already under maintenance");
        }
    }

    public void checkIfCarIsNotUnderMaintenance(UUID carId) {
        if (!repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new EntityNotFoundException("Car is not under maintenance");
        }
    }
}
