package com.turkcellcamp.inventoryservice.business.abstracts;

import com.turkcellcamp.commonpackage.utils.dto.ClientResponse;
import com.turkcellcamp.commonpackage.utils.enums.CarState;
import com.turkcellcamp.inventoryservice.business.dto.requests.create.CreateCarRequest;
import com.turkcellcamp.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import com.turkcellcamp.inventoryservice.business.dto.responses.create.CreateCarResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetCarResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.update.UpdateCarResponse;

import java.util.List;
import java.util.UUID;

public interface CarService {

    List<GetAllCarsResponse> getAll();

    GetCarResponse getById(UUID id);

    CreateCarResponse add(CreateCarRequest request);

    UpdateCarResponse update(UUID id, UpdateCarRequest request);

    void delete(UUID id);

    ClientResponse checkCarAvailability(UUID id);

    void changeStateByCarId(UUID id, CarState state);
}
