package com.turkcellcamp.inventoryservice.api.controllers;

import com.turkcellcamp.commonpackage.utils.constants.Roles;
import com.turkcellcamp.commonpackage.utils.dto.CarResponse;
import com.turkcellcamp.commonpackage.utils.dto.ChangeCarStateRequest;
import com.turkcellcamp.commonpackage.utils.dto.ClientResponse;
import com.turkcellcamp.inventoryservice.business.abstracts.CarService;
import com.turkcellcamp.inventoryservice.business.dto.requests.create.CreateCarRequest;
import com.turkcellcamp.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import com.turkcellcamp.inventoryservice.business.dto.responses.create.CreateCarResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetCarResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cars")
@AllArgsConstructor
public class CarsController {

    private final CarService carService;

    @GetMapping
    @PreAuthorize(Roles.ADMIN_OR_MODERATOR)
    public List<GetAllCarsResponse> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    @PostAuthorize(Roles.ADMIN_OR_MODERATOR + "|| returnObject.modelYear == 2020")
    public GetCarResponse getById(@PathVariable UUID id) {
        return carService.getById(id);
    }

    @GetMapping("/check-car-available/{id}")
    public ClientResponse checkCarAvailability(@PathVariable UUID id) {
        return carService.checkCarAvailability(id);
    }

    @GetMapping("/get-car-info/{id}")
    public CarResponse getCarInformation(@PathVariable UUID id) {
        return carService.getCarInformation(id);
    }

    @PutMapping("/change-car-state")
    public void changeCarState(@RequestBody ChangeCarStateRequest request) {
        carService.changeStateByCarId(request.getCarId(), request.getCarState());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarResponse add(@Valid @RequestBody CreateCarRequest request) {
        return carService.add(request);
    }

    @PutMapping("/{id}")
    public UpdateCarResponse update(@PathVariable UUID id, @RequestBody UpdateCarRequest request) {
        return carService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        carService.delete(id);
    }
}
