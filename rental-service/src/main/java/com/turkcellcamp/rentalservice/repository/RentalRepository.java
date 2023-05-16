package com.turkcellcamp.rentalservice.repository;

import com.turkcellcamp.rentalservice.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RentalRepository extends JpaRepository<Rental, UUID> {

}
