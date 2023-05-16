package com.turkcellcamp.filterservice.business.kafka.consumer;

import com.turkcellcamp.commonpackage.events.inventory.BrandDeletedEvent;
import com.turkcellcamp.commonpackage.events.inventory.CarCreatedEvent;
import com.turkcellcamp.commonpackage.events.inventory.CarDeletedEvent;
import com.turkcellcamp.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellcamp.filterservice.business.abstracts.FilterService;
import com.turkcellcamp.filterservice.entities.Filter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryConsumer {

    private final FilterService service;
    private final ModelMapperService mapper;

    @KafkaListener(
        topics = "car-created",
        groupId = "car-create"
    )
    public void consume(CarCreatedEvent event) {
        Filter filter = mapper.forRequest().map(event, Filter.class);
        service.add(filter);
        log.info("Car created event consumed {}", event);
    }

    @KafkaListener(
            topics = "car-deleted",
            groupId = "car-delete"
    )
    public void consume(CarDeletedEvent event) {
        service.deleteByCarId(event.getCarId());
        log.info("Car deleted event consumed {}", event);
    }

    @KafkaListener(
            topics = "brand-deleted",
            groupId = "brand-delete"
    )
    public void consume(BrandDeletedEvent event) {
        service.deleteAllByBrandId(event.getBrandId());
        log.info("Brand deleted event consumed {}", event);
    }
}
