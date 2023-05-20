package com.turkcellcamp.filterservice.business.abstracts;

import com.turkcellcamp.commonpackage.utils.enums.CarState;
import com.turkcellcamp.filterservice.business.dto.responses.GetAllFiltersResponse;
import com.turkcellcamp.filterservice.business.dto.responses.GetFilterResponse;
import com.turkcellcamp.filterservice.entities.Filter;

import java.util.List;
import java.util.UUID;

public interface FilterService {

    List<GetAllFiltersResponse> getAll();
    GetFilterResponse getById(UUID id);
    void add(Filter filter);
    void delete(UUID id);
    void deleteAllByBrandId(UUID brandId);
    void deleteByCarId(UUID carId);
    Filter getByCarId(UUID carId);
    void changeStateByCarId(UUID carId, CarState carState);
}
