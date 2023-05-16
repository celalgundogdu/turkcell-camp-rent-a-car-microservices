package com.turkcellcamp.inventoryservice.business.concretes;

import com.turkcellcamp.commonpackage.events.inventory.BrandDeletedEvent;
import com.turkcellcamp.commonpackage.utils.kafka.producer.KafkaProducer;
import com.turkcellcamp.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellcamp.inventoryservice.business.abstracts.BrandService;
import com.turkcellcamp.inventoryservice.business.dto.requests.create.CreateBrandRequest;
import com.turkcellcamp.inventoryservice.business.dto.requests.update.UpdateBrandRequest;
import com.turkcellcamp.inventoryservice.business.dto.responses.create.CreateBrandResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetAllBrandsResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetBrandResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.update.UpdateBrandResponse;
import com.turkcellcamp.inventoryservice.business.rules.BrandBusinessRules;
import com.turkcellcamp.inventoryservice.entities.Brand;
import com.turkcellcamp.inventoryservice.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;
    private final BrandBusinessRules rules;
    private final ModelMapperService mapper;
    private final KafkaProducer kafkaProducer;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brandList = repository.findAll();
        List<GetAllBrandsResponse> response = brandList
                .stream()
                .map(brand -> mapper.forResponse().map(brand, GetAllBrandsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetBrandResponse getById(UUID id) {
        rules.checkIfBrandExistsById(id);
        Brand brand =  repository.findById(id).orElseThrow();
        GetBrandResponse response = mapper.forResponse().map(brand, GetBrandResponse.class);
        return response;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
        rules.checkIfBrandExistsByName(request.getName());
        Brand brand = mapper.forRequest().map(request, Brand.class);
        brand.setId(null);
        repository.save(brand);
        CreateBrandResponse response = mapper.forResponse().map(brand, CreateBrandResponse.class);
        return response;
    }

    @Override
    public UpdateBrandResponse update(UUID id, UpdateBrandRequest request) {
        rules.checkIfBrandExistsById(id);
        Brand brand = mapper.forRequest().map(request, Brand.class);
        brand.setId(id);
        repository.save(brand);
        UpdateBrandResponse response = mapper.forResponse().map(brand, UpdateBrandResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfBrandExistsById(id);
        repository.deleteById(id);
        sendKafkaBrandDeletedEvent(id);
    }

    private void sendKafkaBrandDeletedEvent(UUID id) {
        kafkaProducer.sendMessage(new BrandDeletedEvent(id), "brand-deleted");
    }
}
