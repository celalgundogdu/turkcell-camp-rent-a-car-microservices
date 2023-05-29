package com.turkcellcamp.invoiceservice.business.kafka;

import com.turkcellcamp.commonpackage.events.invoice.CreateInvoiceEvent;
import com.turkcellcamp.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellcamp.invoiceservice.business.abstracts.InvoiceService;
import com.turkcellcamp.invoiceservice.business.dto.requests.CreateInvoiceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RentalConsumer {

    private final InvoiceService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "create-invoice",
            groupId = "invoice-rental-create"
    )
    public void consume(CreateInvoiceEvent event) {
        CreateInvoiceRequest request = mapper.forRequest().map(event, CreateInvoiceRequest.class);
        service.add(request);
        log.info("Create invoice event consumed {}", event);
    }
}
