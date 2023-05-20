package com.turkcellcamp.inventoryservice.repository;

import com.turkcellcamp.commonpackage.utils.enums.CarState;
import com.turkcellcamp.inventoryservice.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {

    boolean existsByPlateIgnoreCase(String plate);

    @Modifying
    @Transactional
    @Query(value = "update Car set state =:state where id =:id")
    void changeStateByCarId(UUID id, CarState state);
}
