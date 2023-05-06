package com.turkcellcamp.inventoryservice.business.rules;

import com.turkcellcamp.inventoryservice.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandBusinessRules {

    private final BrandRepository repository;

    public void checkIfBrandExistsById(UUID id) {
        if (!repository.existsById(id)){
            throw new RuntimeException("Brand not found");
        }
    }

    public void checkIfBrandExistsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new RuntimeException("Brand name already exists");
        }
    }
}
