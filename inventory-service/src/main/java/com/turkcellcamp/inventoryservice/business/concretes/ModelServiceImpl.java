package com.turkcellcamp.inventoryservice.business.concretes;

import com.turkcellcamp.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellcamp.inventoryservice.business.abstracts.ModelService;
import com.turkcellcamp.inventoryservice.business.dto.requests.create.CreateModelRequest;
import com.turkcellcamp.inventoryservice.business.dto.requests.update.UpdateModelRequest;
import com.turkcellcamp.inventoryservice.business.dto.responses.create.CreateModelResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetAllModelsResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetModelResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.update.UpdateModelResponse;
import com.turkcellcamp.inventoryservice.business.rules.ModelBusinessRules;
import com.turkcellcamp.inventoryservice.entities.Model;
import com.turkcellcamp.inventoryservice.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository repository;
    private final ModelBusinessRules rules;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> modelList = repository.findAll();
        List<GetAllModelsResponse> response = modelList
                .stream()
                .map(model -> mapper.forResponse().map(model, GetAllModelsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetModelResponse getById(UUID id) {
        rules.checkIfModelExistsById(id);
        Model model = repository.findById(id).orElseThrow();
        GetModelResponse response = mapper.forResponse().map(model, GetModelResponse.class);
        return response;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        rules.checkIfModelExistsByName(request.getName());
        Model model = mapper.forRequest().map(request, Model.class);
        model.setId(null);
        repository.save(model);
        CreateModelResponse response = mapper.forResponse().map(model, CreateModelResponse.class);
        return response;
    }

    @Override
    public UpdateModelResponse update(UUID id, UpdateModelRequest request) {
        rules.checkIfModelExistsById(id);
        Model model = mapper.forRequest().map(request, Model.class);
        model.setId(id);
        Model createdModel = repository.save(model);
        UpdateModelResponse response = mapper.forResponse().map(createdModel, UpdateModelResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfModelExistsById(id);
        repository.deleteById(id);
    }
}
