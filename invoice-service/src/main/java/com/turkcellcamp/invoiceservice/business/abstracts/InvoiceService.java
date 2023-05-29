package com.turkcellcamp.invoiceservice.business.abstracts;

import com.turkcellcamp.invoiceservice.business.dto.requests.CreateInvoiceRequest;
import com.turkcellcamp.invoiceservice.business.dto.requests.UpdateInvoiceRequest;
import com.turkcellcamp.invoiceservice.business.dto.responses.CreateInvoiceResponse;
import com.turkcellcamp.invoiceservice.business.dto.responses.GetAllInvoicesResponse;
import com.turkcellcamp.invoiceservice.business.dto.responses.GetInvoiceResponse;
import com.turkcellcamp.invoiceservice.business.dto.responses.UpdateInvoiceResponse;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {

    List<GetAllInvoicesResponse> getAll();

    GetInvoiceResponse getById(String id);

    CreateInvoiceResponse add(CreateInvoiceRequest request);

    UpdateInvoiceResponse update(String id, UpdateInvoiceRequest request);

    void delete(String id);
}
