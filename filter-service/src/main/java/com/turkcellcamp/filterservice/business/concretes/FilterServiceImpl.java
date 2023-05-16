package com.turkcellcamp.filterservice.business.concretes;

import com.turkcellcamp.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellcamp.filterservice.business.abstracts.FilterService;
import com.turkcellcamp.filterservice.business.dto.responses.GetAllFiltersResponse;
import com.turkcellcamp.filterservice.business.dto.responses.GetFilterResponse;
import com.turkcellcamp.filterservice.entities.Filter;
import com.turkcellcamp.filterservice.repository.FilterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FilterServiceImpl implements FilterService {

    private final FilterRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllFiltersResponse> getAll() {
        List<Filter> filterList = repository.findAll();
        List<GetAllFiltersResponse> response = filterList
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFiltersResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetFilterResponse getById(UUID id) {
        Filter filter = repository.findById(id).orElseThrow(RuntimeException::new);
        GetFilterResponse response = mapper.forResponse().map(filter, GetFilterResponse.class);
        return response;
    }

    @Override
    public void add(Filter filter) {
        filter.setId(UUID.randomUUID());
        repository.save(filter);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllByBrandId(UUID brandId) {
        repository.deleteAllByBrandId(brandId);
    }

    @Override
    public void deleteByCarId(UUID carId) {
        repository.deleteByCarId(carId);
    }

    @Override
    public Filter getByCarId(UUID carId) {
        return repository.findByCarId(carId);
    }
}
