package com.turkcellcamp.inventoryservice.business.kafka.consumer;

import com.turkcellcamp.commonpackage.events.rental.RentalCreatedEvent;
import com.turkcellcamp.inventoryservice.business.abstracts.CarService;
import com.turkcellcamp.inventoryservice.entities.enums.CarState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RentalConsumer {

    private final CarService service;

    @KafkaListener(
            topics = "rental-created",
            groupId = "inventory-rental-create"
    )
    public void consume(RentalCreatedEvent event) {
        service.changeStateByCarId(event.getCarId(), CarState.RENTED);
        log.info("Rental created event consumed {}", event);
    }
}
