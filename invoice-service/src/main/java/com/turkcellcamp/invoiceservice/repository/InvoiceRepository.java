package com.turkcellcamp.invoiceservice.repository;

import com.turkcellcamp.invoiceservice.entities.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface InvoiceRepository extends MongoRepository<Invoice, String> {

}
