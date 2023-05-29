package com.turkcellcamp.invoiceservice.business.concretes;

import com.turkcellcamp.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellcamp.invoiceservice.business.abstracts.InvoiceService;
import com.turkcellcamp.invoiceservice.business.dto.requests.CreateInvoiceRequest;
import com.turkcellcamp.invoiceservice.business.dto.requests.UpdateInvoiceRequest;
import com.turkcellcamp.invoiceservice.business.dto.responses.CreateInvoiceResponse;
import com.turkcellcamp.invoiceservice.business.dto.responses.GetAllInvoicesResponse;
import com.turkcellcamp.invoiceservice.business.dto.responses.GetInvoiceResponse;
import com.turkcellcamp.invoiceservice.business.dto.responses.UpdateInvoiceResponse;
import com.turkcellcamp.invoiceservice.business.rules.InvoiceBusinessRules;
import com.turkcellcamp.invoiceservice.entities.Invoice;
import com.turkcellcamp.invoiceservice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceBusinessRules rules;
    private final ModelMapperService mapper;
    private final InvoiceRepository repository;

    @Override
    public List<GetAllInvoicesResponse> getAll() {
        List<Invoice> invoiceList = repository.findAll();
        List<GetAllInvoicesResponse> response = invoiceList
                .stream()
                .map(invoice -> mapper.forResponse().map(invoice, GetAllInvoicesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetInvoiceResponse getById(String id) {
        rules.checkIfInvoiceExistsById(id);
        Invoice invoice = repository.findById(id).orElseThrow();
        GetInvoiceResponse response = mapper.forResponse().map(invoice, GetInvoiceResponse.class);
        return response;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest request) {
        Invoice invoice = mapper.forRequest().map(request, Invoice.class);
        invoice.setId(null);
        invoice.setTotalPrice(calculateTotalPrice(invoice));
        Invoice createdInvoice = repository.save(invoice);
        CreateInvoiceResponse response = mapper.forResponse().map(createdInvoice, CreateInvoiceResponse.class);
        return response;
    }

    @Override
    public UpdateInvoiceResponse update(String id, UpdateInvoiceRequest request) {
        rules.checkIfInvoiceExistsById(id);
        Invoice invoice = mapper.forRequest().map(request, Invoice.class);
        invoice.setId(id);
        invoice.setTotalPrice(calculateTotalPrice(invoice));
        Invoice updatedInvoice = repository.save(invoice);
        UpdateInvoiceResponse response = mapper.forResponse().map(updatedInvoice, UpdateInvoiceResponse.class);
        return response;
    }

    @Override
    public void delete(String id) {
        rules.checkIfInvoiceExistsById(id);
        repository.deleteById(id);
    }

    private double calculateTotalPrice(Invoice invoice) {
        return invoice.getDailyPrice() * invoice.getRentedForDays();
    }
}
