package com.turkcellcamp.inventoryservice.business.concretes;

import com.turkcellcamp.commonpackage.events.inventory.CarCreatedEvent;
import com.turkcellcamp.commonpackage.events.inventory.CarDeletedEvent;
import com.turkcellcamp.commonpackage.utils.kafka.producer.KafkaProducer;
import com.turkcellcamp.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellcamp.inventoryservice.business.abstracts.CarService;
import com.turkcellcamp.inventoryservice.business.dto.requests.create.CreateCarRequest;
import com.turkcellcamp.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import com.turkcellcamp.inventoryservice.business.dto.responses.create.CreateCarResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.get.GetCarResponse;
import com.turkcellcamp.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import com.turkcellcamp.inventoryservice.business.rules.CarBusinessRules;
import com.turkcellcamp.inventoryservice.entities.Car;
import com.turkcellcamp.inventoryservice.entities.enums.CarState;
import com.turkcellcamp.inventoryservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository repository;
    private final CarBusinessRules rules;
    private final ModelMapperService mapper;
    private final KafkaProducer kafkaProducer;

    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> carList = repository.findAll();
        List<GetAllCarsResponse> response = carList
                .stream()
                .map(car -> mapper.forResponse().map(car, GetAllCarsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetCarResponse getById(UUID id) {
        rules.checkIfCarExistsById(id);
        Car car = repository.findById(id).orElseThrow();
        GetCarResponse response = mapper.forResponse().map(car, GetCarResponse.class);
        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        rules.checkIfCarExistsByPlate(request.getPlate());
        Car car = mapper.forRequest().map(request, Car.class);
        car.setId(UUID.randomUUID());
        car.setState(CarState.AVAILABLE);
        Car createdCar = repository.save(car);
        sendKafkaCarCreatedEvent(createdCar);
        CreateCarResponse response = mapper.forResponse().map(createdCar, CreateCarResponse.class);
        return response;
    }

    @Override
    public UpdateCarResponse update(UUID id, UpdateCarRequest request) {
        rules.checkIfCarExistsById(id);
        Car car = mapper.forRequest().map(request, Car.class);
        car.setId(id);
        Car updatedCar = repository.save(car);
        UpdateCarResponse response = mapper.forResponse().map(updatedCar, UpdateCarResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfCarExistsById(id);
        repository.deleteById(id);
        sendKafkaCarDeletedEvent(id);
    }

    @Override
    public void checkCarAvailability(UUID id) {
        rules.checkIfCarExistsById(id);
        rules.checkIfCarAvailable(id);
    }

    @Override
    public void changeStateByCarId(UUID id, CarState state) {
        repository.changeStateByCarId(id, state);
    }

    private void sendKafkaCarCreatedEvent(Car createdCar) {
        CarCreatedEvent event = mapper.forResponse().map(createdCar, CarCreatedEvent.class);
        kafkaProducer.sendMessage(event, "car-created");
    }

    private void sendKafkaCarDeletedEvent(UUID id) {
        kafkaProducer.sendMessage(new CarDeletedEvent(id), "car-deleted");
    }
}
