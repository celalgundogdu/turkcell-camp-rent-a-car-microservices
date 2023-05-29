package com.turkcellcamp.invoiceservice.business.rules;

import com.turkcellcamp.commonpackage.utils.constants.Messages;
import com.turkcellcamp.commonpackage.utils.exceptions.EntityNotFoundException;
import com.turkcellcamp.invoiceservice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceBusinessRules {

    private final InvoiceRepository repository;

    public void checkIfInvoiceExistsById(String id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(Messages.Invoice.NOT_EXISTS);
        }
    }
}
