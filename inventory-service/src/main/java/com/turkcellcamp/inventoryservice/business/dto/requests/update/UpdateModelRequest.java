package com.turkcellcamp.inventoryservice.business.dto.requests.update;

import jakarta.validation.constraints.NotBlank;
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
public class UpdateModelRequest {

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @NotBlank
    private UUID brandId;
}
