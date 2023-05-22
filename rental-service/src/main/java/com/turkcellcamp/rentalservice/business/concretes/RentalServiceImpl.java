package com.turkcellcamp.rentalservice.business.concretes;

import com.turkcellcamp.commonpackage.events.rental.RentalCreatedEvent;
import com.turkcellcamp.commonpackage.events.rental.RentalDeletedEvent;
import com.turkcellcamp.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.turkcellcamp.commonpackage.utils.kafka.producer.KafkaProducer;
import com.turkcellcamp.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellcamp.rentalservice.business.abstracts.RentalService;
import com.turkcellcamp.rentalservice.business.dto.requests.CreateRentalRequest;
import com.turkcellcamp.rentalservice.business.dto.requests.UpdateRentalRequest;
import com.turkcellcamp.rentalservice.business.dto.responses.CreateRentalResponse;
import com.turkcellcamp.rentalservice.business.dto.responses.GetAllRentalsResponse;
import com.turkcellcamp.rentalservice.business.dto.responses.GetRentalResponse;
import com.turkcellcamp.rentalservice.business.dto.responses.UpdateRentalResponse;
import com.turkcellcamp.rentalservice.business.rules.RentalBusinessRules;
import com.turkcellcamp.rentalservice.entities.Rental;
import com.turkcellcamp.rentalservice.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository repository;
    private final ModelMapperService mapper;
    private final RentalBusinessRules rules;
    private final KafkaProducer kafkaProducer;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        List<Rental> rentalList = repository.findAll();
        List<GetAllRentalsResponse> response = rentalList
                .stream()
                .map(rental -> mapper.forResponse().map(rental, GetAllRentalsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetRentalResponse getById(UUID id) {
        rules.checkIfRentalExists(id);
        Rental rental = repository.findById(id).orElseThrow();
        GetRentalResponse response = mapper.forResponse().map(rental, GetRentalResponse.class);
        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        rules.ensureCarIsAvailable(request.getCarId());
        Rental rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(null);
        rental.setTotalPrice(calculateTotalPrice(rental));
        rental.setRentedAt(LocalDate.now());

        CreateRentalPaymentRequest paymentRequest = new CreateRentalPaymentRequest();
        mapper.forRequest().map(request.getPaymentRequest(), paymentRequest);
        paymentRequest.setPrice(rental.getTotalPrice());
        rules.processRentalPayment(paymentRequest);

        Rental createdRental = repository.save(rental);
        sendKafkaRentalCreatedEvent(request.getCarId());
        CreateRentalResponse response = mapper.forResponse().map(createdRental, CreateRentalResponse.class);
        return response;
    }

    @Override
    public UpdateRentalResponse update(UUID id, UpdateRentalRequest request) {
        rules.checkIfRentalExists(id);
        Rental rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(id);
        Rental updatedRental = repository.save(rental);
        UpdateRentalResponse response = mapper.forResponse().map(updatedRental, UpdateRentalResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfRentalExists(id);
        sendKafkaRentalDeletedEvent(id);
        repository.deleteById(id);
    }

    private double calculateTotalPrice(Rental rental) {
        return rental.getRentedForDays() * rental.getDailyPrice();
    }

    private void sendKafkaRentalCreatedEvent(UUID carId) {
        kafkaProducer.sendMessage(new RentalCreatedEvent(carId), "rental-created");
    }

    private void sendKafkaRentalDeletedEvent(UUID id) {
        UUID carId = repository.findById(id).orElseThrow().getCarId();
        kafkaProducer.sendMessage(new RentalDeletedEvent(carId), "rental-deleted");
    }
}
