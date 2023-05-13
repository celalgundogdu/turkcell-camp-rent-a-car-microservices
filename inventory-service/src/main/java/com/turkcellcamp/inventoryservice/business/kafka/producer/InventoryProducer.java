package com.turkcellcamp.inventoryservice.business.kafka.producer;

import com.turkcellcamp.commonpackage.events.inventory.BrandDeletedEvent;
import com.turkcellcamp.commonpackage.events.inventory.CarCreatedEvent;
import com.turkcellcamp.commonpackage.events.inventory.CarDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryProducer {

    private static Logger LOGGER = LoggerFactory.getLogger(InventoryProducer.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(CarCreatedEvent event) {
        LOGGER.info(String.format("car-created event => %s", event.toString()));
        Message<CarCreatedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "car-created")
                .build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(CarDeletedEvent event) {
        LOGGER.info(String.format("car-deleted event => %s", event.toString()));
        Message<CarDeletedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "car-deleted")
                .build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(BrandDeletedEvent event) {
        LOGGER.info(String.format("brand-deleted event => %s", event.toString()));
        Message<BrandDeletedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "brand-deleted")
                .build();

        kafkaTemplate.send(message);
    }
}
