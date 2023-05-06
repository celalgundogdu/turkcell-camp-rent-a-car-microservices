package com.turkcellcamp.inventoryservice.business.rules;

import com.turkcellcamp.inventoryservice.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ModelBusinessRules {

    private final ModelRepository repository;

    public void checkIfModelExistsById(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Model not found");
        }
    }

    public void checkIfModelExistsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new RuntimeException("Model name already exists");
        }
    }
}
