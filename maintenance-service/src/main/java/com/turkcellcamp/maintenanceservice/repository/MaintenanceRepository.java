package com.turkcellcamp.maintenanceservice.repository;

import com.turkcellcamp.maintenanceservice.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaintenanceRepository extends JpaRepository<Maintenance, UUID> {

    Maintenance findByCarIdAndIsCompletedIsFalse(UUID carId);

    boolean existsByCarIdAndIsCompletedIsFalse(UUID carId);
}