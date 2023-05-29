package com.turkcellcamp.invoiceservice;

import com.turkcellcamp.commonpackage.utils.constants.Paths;
import com.turkcellcamp.invoiceservice.business.dto.requests.CreateInvoiceRequest;
import com.turkcellcamp.invoiceservice.entities.Invoice;
import com.turkcellcamp.invoiceservice.repository.InvoiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.time.LocalDate;

@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage, Paths.Invoice.ServiceBasePackage})
@EnableDiscoveryClient
public class InvoiceServiceApplication {

	private InvoiceRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(InvoiceServiceApplication.class, args);
	}

}
