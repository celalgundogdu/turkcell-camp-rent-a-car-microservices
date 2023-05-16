package com.turkcellcamp.filterservice.business.kafka.consumer;

import com.turkcellcamp.commonpackage.events.rental.RentalCreatedEvent;
import com.turkcellcamp.commonpackage.events.rental.RentalDeletedEvent;
import com.turkcellcamp.filterservice.business.abstracts.FilterService;
import com.turkcellcamp.filterservice.business.dto.responses.GetFilterResponse;
import com.turkcellcamp.filterservice.entities.Filter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RentalConsumer {

    private final FilterService service;

    @KafkaListener(
            topics = "rental-created",
            groupId = "filter-rental-create"
    )
    public void consume(RentalCreatedEvent event) {
        Filter filter = service.getByCarId(event.getCarId());
        filter.setState("RENTED");
        service.add(filter);
        log.info("Rental created event consumed {}", event);
    }

    @KafkaListener(
            topics = "rental-deleted",
            groupId = "filter-rental-service"
    )
    public void consume(RentalDeletedEvent event) {
        Filter filter = service.getByCarId(event.getCarId());
        filter.setState("AVAILABLE");
        service.add(filter);
        log.info("Rental deleted event consumed {}", event);
    }
}
