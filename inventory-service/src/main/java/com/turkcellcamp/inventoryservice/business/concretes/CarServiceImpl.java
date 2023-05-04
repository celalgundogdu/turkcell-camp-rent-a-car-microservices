package com.turkcellcamp.inventoryservice.business.concretes;

import com.turkcellcamp.inventoryservice.business.abstracts.CarService;
import com.turkcellcamp.inventoryservice.business.dto.requests.create.CreateCarRequest;
import com.turkcellcamp.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import com.turkcellcamp.inventoryservice.business.dto.responses.create.CreateCarResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetCarResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    @Override
    public List<GetAllCarsResponse> getAll(boolean includeMaintenance) {
        return null;
    }

    @Override
    public GetCarResponse getById(UUID id) {
        return null;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        return null;
    }

    @Override
    public UpdateCarResponse update(UUID id, UpdateCarRequest request) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
