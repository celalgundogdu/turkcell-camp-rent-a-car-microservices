package com.turkcellcamp.filterservice.api.controllers;

import com.turkcellcamp.filterservice.business.abstracts.FilterService;
import com.turkcellcamp.filterservice.business.dto.responses.GetAllFiltersResponse;
import com.turkcellcamp.filterservice.business.dto.responses.GetFilterResponse;
import com.turkcellcamp.filterservice.entities.Filter;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/filters")
public class FiltersController {

    private final FilterService service;

    @PostConstruct
    public void createDb() {
        service.add(new Filter());
    }

    @GetMapping
    public List<GetAllFiltersResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetFilterResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }
}
