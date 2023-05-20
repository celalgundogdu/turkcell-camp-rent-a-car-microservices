package com.turkcellcamp.commonpackage.utils.dto;

import com.turkcellcamp.commonpackage.utils.enums.CarState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeCarStateRequest {

    private UUID carId;
    private CarState carState;
}
