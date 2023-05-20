package com.turkcellcamp.maintenanceservice.business.abstracts;

import com.turkcellcamp.maintenanceservice.business.dto.requests.CreateMaintenanceRequest;
import com.turkcellcamp.maintenanceservice.business.dto.requests.UpdateMaintenanceRequest;
import com.turkcellcamp.maintenanceservice.business.dto.responses.CreateMaintenanceResponse;
import com.turkcellcamp.maintenanceservice.business.dto.responses.GetAllMaintenancesResponse;
import com.turkcellcamp.maintenanceservice.business.dto.responses.GetMaintenanceResponse;
import com.turkcellcamp.maintenanceservice.business.dto.responses.UpdateMaintenanceResponse;

import java.util.List;
import java.util.UUID;

public interface MaintenanceService {

    List<GetAllMaintenancesResponse> getAll();

    GetMaintenanceResponse getById(UUID id);

    GetMaintenanceResponse returnCarFromMaintenance(UUID carId);

    CreateMaintenanceResponse add(CreateMaintenanceRequest request);

    UpdateMaintenanceResponse update(UUID id, UpdateMaintenanceRequest request);

    void delete(UUID id);
}
