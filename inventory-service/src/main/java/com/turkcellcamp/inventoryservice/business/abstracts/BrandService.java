package com.turkcellcamp.inventoryservice.business.abstracts;

import com.turkcellcamp.inventoryservice.business.dto.requests.create.CreateBrandRequest;
import com.turkcellcamp.inventoryservice.business.dto.requests.update.UpdateBrandRequest;
import com.turkcellcamp.inventoryservice.business.dto.responses.create.CreateBrandResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetAllBrandsResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetBrandResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.update.UpdateBrandResponse;

import java.util.List;
import java.util.UUID;

public interface BrandService {

    List<GetAllBrandsResponse> getAll();

    GetBrandResponse getById(UUID id);

    CreateBrandResponse add(CreateBrandRequest request);

    UpdateBrandResponse update(UUID id, UpdateBrandRequest request);

    void delete(UUID id);
}
