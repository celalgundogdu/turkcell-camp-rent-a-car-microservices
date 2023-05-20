package com.turkcellcamp.filterservice.api.controllers;

import com.turkcellcamp.commonpackage.utils.dto.ChangeCarStateRequest;
import com.turkcellcamp.filterservice.business.abstracts.FilterService;
import com.turkcellcamp.filterservice.business.dto.responses.GetAllFiltersResponse;
import com.turkcellcamp.filterservice.business.dto.responses.GetFilterResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/filters")
public class FiltersController {

    private final FilterService service;

    @GetMapping
    public List<GetAllFiltersResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetFilterResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/change-car-state")
    public void changeCarState(@RequestBody ChangeCarStateRequest request) {
        service.changeStateByCarId(request.getCarId(), request.getCarState());
    }
}
