package com.turkcellcamp.inventoryservice.business.concretes;

import com.turkcellcamp.inventoryservice.business.abstracts.BrandService;
import com.turkcellcamp.inventoryservice.business.dto.requests.create.CreateBrandRequest;
import com.turkcellcamp.inventoryservice.business.dto.requests.update.UpdateBrandRequest;
import com.turkcellcamp.inventoryservice.business.dto.responses.create.CreateBrandResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetAllBrandsResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetBrandResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.update.UpdateBrandResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    @Override
    public List<GetAllBrandsResponse> getAll() {
        return null;
    }

    @Override
    public GetBrandResponse getById(UUID id) {
        return null;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
        return null;
    }

    @Override
    public UpdateBrandResponse update(UUID id, UpdateBrandRequest request) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
