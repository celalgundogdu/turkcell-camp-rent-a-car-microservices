package com.turkcellcamp.rentalservice.api.clients;

import com.turkcellcamp.commonpackage.utils.dto.CarResponse;
import com.turkcellcamp.commonpackage.utils.dto.ClientResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "INVENTORY-SERVICE", fallback = CarClientFallback.class)
public interface CarClient {

    @GetMapping(value = "/api/v1/cars/check-car-available/{carId}")
    @Retry(name = "carAvailability")
    ClientResponse checkIfCarAvailable(@PathVariable UUID carId);

    @GetMapping("/api/v1/cars/get-car-info/{id}")
    CarResponse getCarInformation(@PathVariable UUID id);
}
