package com.turkcellcamp.inventoryservice.business.dto.requests.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateModelRequest {

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    private UUID brandId;
}
