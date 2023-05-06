package com.turkcellcamp.inventoryservice.repository;

import com.turkcellcamp.inventoryservice.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModelRepository extends JpaRepository<Model, UUID> {

    boolean existsByNameIgnoreCase(String name);
}
