package com.turkcellcamp.filterservice.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllFiltersResponse {

    private String id;
    private UUID carId;
    private UUID modelId;
    private UUID brandId;
}
