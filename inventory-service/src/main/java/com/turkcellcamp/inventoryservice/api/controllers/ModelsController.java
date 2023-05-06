package com.turkcellcamp.inventoryservice.api.controllers;

import com.turkcellcamp.inventoryservice.business.abstracts.ModelService;
import com.turkcellcamp.inventoryservice.business.dto.requests.create.CreateModelRequest;
import com.turkcellcamp.inventoryservice.business.dto.requests.update.UpdateModelRequest;
import com.turkcellcamp.inventoryservice.business.dto.responses.create.CreateModelResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetAllModelsResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetModelResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.update.UpdateModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/models")
@AllArgsConstructor
public class ModelsController {

    private final ModelService modelService;

    @GetMapping
    public List<GetAllModelsResponse> getAll() {
        return modelService.getAll();
    }

    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable UUID id) {
        return modelService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateModelResponse add(@RequestBody CreateModelRequest request) {
        return modelService.add(request);
    }

    @PutMapping("/{id}")
    public UpdateModelResponse update(@PathVariable UUID id, @RequestBody UpdateModelRequest request) {
        return modelService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        modelService.delete(id);
    }
}
