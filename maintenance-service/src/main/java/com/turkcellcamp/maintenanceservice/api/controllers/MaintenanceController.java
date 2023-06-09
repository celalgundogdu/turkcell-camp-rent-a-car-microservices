package com.turkcellcamp.maintenanceservice.api.controllers;

import com.turkcellcamp.maintenanceservice.business.abstracts.MaintenanceService;
import com.turkcellcamp.maintenanceservice.business.dto.requests.CreateMaintenanceRequest;
import com.turkcellcamp.maintenanceservice.business.dto.requests.UpdateMaintenanceRequest;
import com.turkcellcamp.maintenanceservice.business.dto.responses.CreateMaintenanceResponse;
import com.turkcellcamp.maintenanceservice.business.dto.responses.GetAllMaintenancesResponse;
import com.turkcellcamp.maintenanceservice.business.dto.responses.GetMaintenanceResponse;
import com.turkcellcamp.maintenanceservice.business.dto.responses.UpdateMaintenanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @GetMapping
    public List<GetAllMaintenancesResponse> getAll() {
        return maintenanceService.getAll();
    }

    @GetMapping("/{id}")
    public GetMaintenanceResponse getById(@PathVariable UUID id) {
        return maintenanceService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMaintenanceResponse add(@RequestBody CreateMaintenanceRequest request) {
        return maintenanceService.add(request);
    }

    @PutMapping("/{id}")
    public UpdateMaintenanceResponse update(@PathVariable UUID id, @RequestBody UpdateMaintenanceRequest request) {
        return maintenanceService.update(id, request);
    }

    @PutMapping("/return")
    public GetMaintenanceResponse returnCarFromMaintenance(@RequestParam UUID carId) {
        return maintenanceService.returnCarFromMaintenance(carId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        maintenanceService.delete(id);
    }
}
