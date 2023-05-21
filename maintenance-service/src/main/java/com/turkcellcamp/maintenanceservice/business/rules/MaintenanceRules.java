package com.turkcellcamp.maintenanceservice.business.rules;

import com.turkcellcamp.commonpackage.utils.dto.ClientResponse;
import com.turkcellcamp.commonpackage.utils.exceptions.BusinessException;
import com.turkcellcamp.commonpackage.utils.exceptions.EntityAlreadyExistsException;
import com.turkcellcamp.commonpackage.utils.exceptions.EntityNotFoundException;
import com.turkcellcamp.maintenanceservice.api.clients.CarClient;
import com.turkcellcamp.maintenanceservice.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MaintenanceRules {

    private final MaintenanceRepository repository;
    private final CarClient carClient;

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

    public void ensureCarIsAvailable(UUID carId) {
        ClientResponse clientResponse = carClient.checkIfCarAvailable(carId);
        if (!clientResponse.isSuccess()) {
            throw new BusinessException(clientResponse.getMessage());
        }
    }
}
