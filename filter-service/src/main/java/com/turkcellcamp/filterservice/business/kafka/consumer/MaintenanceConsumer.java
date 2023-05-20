package com.turkcellcamp.filterservice.business.kafka.consumer;

import com.turkcellcamp.commonpackage.events.maintenance.MaintenanceCompletedEvent;
import com.turkcellcamp.commonpackage.events.rental.RentalCreatedEvent;
import com.turkcellcamp.filterservice.business.abstracts.FilterService;
import com.turkcellcamp.filterservice.entities.Filter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MaintenanceConsumer {

    private final FilterService service;

    @KafkaListener(
            topics = "maintenance-completed",
            groupId = "filter-maintenance-complete"
    )
    public void consume(MaintenanceCompletedEvent event) {
        Filter filter = service.getByCarId(event.getCarId());
        filter.setState("AVAILABLE");
        service.add(filter);
        log.info("Maintenance completed event consumed {}", event);
    }
}
