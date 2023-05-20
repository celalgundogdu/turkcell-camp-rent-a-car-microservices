package com.turkcellcamp.inventoryservice.business.kafka.consumer;

import com.turkcellcamp.commonpackage.events.maintenance.MaintenanceCompletedEvent;
import com.turkcellcamp.commonpackage.events.rental.RentalCreatedEvent;
import com.turkcellcamp.commonpackage.utils.enums.CarState;
import com.turkcellcamp.inventoryservice.business.abstracts.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MaintenanceConsumer {

    private final CarService service;

    @KafkaListener(
            topics = "maintenance-completed",
            groupId = "inventory-maintenance-complete"
    )
    public void consume(MaintenanceCompletedEvent event) {
        service.changeStateByCarId(event.getCarId(), CarState.AVAILABLE);
        log.info("maintenance completed event consumed {}", event);
    }
}
