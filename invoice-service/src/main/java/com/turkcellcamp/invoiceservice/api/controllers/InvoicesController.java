package com.turkcellcamp.invoiceservice.api.controllers;

import com.turkcellcamp.invoiceservice.business.abstracts.InvoiceService;
import com.turkcellcamp.invoiceservice.business.dto.requests.CreateInvoiceRequest;
import com.turkcellcamp.invoiceservice.business.dto.requests.UpdateInvoiceRequest;
import com.turkcellcamp.invoiceservice.business.dto.responses.CreateInvoiceResponse;
import com.turkcellcamp.invoiceservice.business.dto.responses.GetAllInvoicesResponse;
import com.turkcellcamp.invoiceservice.business.dto.responses.GetInvoiceResponse;
import com.turkcellcamp.invoiceservice.business.dto.responses.UpdateInvoiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoices")
public class InvoicesController {

    private final InvoiceService invoiceService;

    @GetMapping
    public List<GetAllInvoicesResponse> getAll() {
        return invoiceService.getAll();
    }

    @GetMapping("/{id}")
    public GetInvoiceResponse getById(@PathVariable String id) {
        return invoiceService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateInvoiceResponse add(@RequestBody CreateInvoiceRequest request) {
        return invoiceService.add(request);
    }

    @PutMapping("/{id}")
    public UpdateInvoiceResponse update(@PathVariable String id, @RequestBody UpdateInvoiceRequest request) {
        return invoiceService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        invoiceService.delete(id);
    }
}
