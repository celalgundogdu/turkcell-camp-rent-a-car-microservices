package com.turkcellcamp.commonpackage.utils.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {

    private String plate;
    private String modelName;
    private String brandName;
    private int modelYear;
}
